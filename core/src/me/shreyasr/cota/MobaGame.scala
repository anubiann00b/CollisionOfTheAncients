package me.shreyasr.cota

import java.io.IOException

import com.badlogic.ashley.core.{Engine, Entity, Family}
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.{Color, OrthographicCamera}
import com.badlogic.gdx.graphics.g2d.{BitmapFont, SpriteBatch}
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.badlogic.gdx.{ApplicationAdapter, Gdx}
import com.esotericsoftware.kryonet.{Listener, _}
import com.twitter.chill.ScalaKryoInstantiator
import me.shreyasr.cota.MobaGame.RenderingRes
import me.shreyasr.cota.component.{RenderDataComponent, StateDataComponent}
import me.shreyasr.cota.network.{KryoRegistrar, ListQueuedListener, PacketQueue, PacketToClient}
import me.shreyasr.cota.system.render.util._
import me.shreyasr.cota.system.render._
import me.shreyasr.cota.system._
import me.shreyasr.cota.util.{EntityFactory, Vec2}
import me.shreyasr.cota.util.asset.Asset

import scala.collection.JavaConverters._

object MobaGame {

  val FRAME_BUFFER = 2
  val WORLD_WIDTH = 6144
  val WORLD_HEIGHT = 6144

  val GLOBAL_FPS = 60

  class RenderingRes extends ClientRes {
    val batch = new SpriteBatch
    val shape = new ShapeRenderer
    shape.setAutoShapeType(true)
    val assetManager = new AssetManager
    val font = new BitmapFont

    val camera = new OrthographicCamera(640, 480)
//    val viewport = new ExtendViewport(1000, 750, 1600, 900, camera)
    val viewport = new ExtendViewport(800, 600, 1280, 720, camera)
  }

  class ClientRes extends BaseRes {
    val player = EntityFactory.createRenderablePlayer()
    var lastPacketPing = -1l
    val client = new Client(8192, 2048, new KryoSerialization(new ScalaKryoInstantiator().newKryo()))
    val listener = new ListQueuedListener(new Listener {
      override def received(conn: Connection, obj: scala.Any): Unit = {
        obj match {
          case packet: PacketToClient =>
            lastPacketPing = System.currentTimeMillis()-packet.time
            packetQueue.addPacket(packet)
          case _: FrameworkMessage =>
        }
      }
    })
    val packetQueue = new PacketQueue()
  }

  class BaseRes {
    val engine = new Engine
  }
}
class MobaGame extends ApplicationAdapter {

  lazy val res = new RenderingRes()
  import res._


  override def create() {
    Asset.loadAll(assetManager)

    engine.addEntity(player)

    val p = { var i = 0; () => { i += 1; i} }
    engine.addSystem(new InputSendSystem(p(), res, (x, y) => new Vec2(viewport.unproject(new Vector2(x, y)))))
    engine.addSystem(new PacketProcessSystem(p(), res))
    engine.addSystem(new UpdateSystem(p(), res))
    engine.addSystem(new CollisionSystem(p()))
    engine.addSystem(new VelUpdateSystem(p()))

    engine.addSystem(new RenderDataUpdateSystem(p(), res))

    engine.addSystem(new CameraUpdateSystem(p(), res))
    engine.addSystem(new PreRenderSystem(p()))
    engine.addSystem(new   MapRenderSystem(p(), res))
      engine.addSystem(new PreBatchRenderSystem(p(), res))
    engine.addSystem(new ProjectionSystem(p(), res))
    engine.addSystem(new BasicRenderSystem(p()) {
      override def update(deltaTime: Float): Unit = {
        font.draw(batch, lastPacketPing.toString, 8, Gdx.graphics.getHeight - 8)
        font.draw(batch, packetQueue.numPackets.toString, 8, Gdx.graphics.getHeight - 24)
        font.draw(batch, Gdx.graphics.getFramesPerSecond.toString,
          Gdx.graphics.getWidth - 24, Gdx.graphics.getHeight - 8)
      }
    })
    engine.addSystem(new   MainRenderSystem(p(), res))
    engine.addSystem(new ShapeRenderSystem(p(), res))
    engine.addSystem(new IteratingRenderSystem(Family.all(classOf[RenderDataComponent]).get(), p()) {
      override def processEntity(entity: Entity, deltaTime: Float): Unit = {
        val rect = entity.get[StateDataComponent].rect
        val pos = entity.get[StateDataComponent].pos
        res.shape.setColor(Color.RED)
        res.shape.rect(rect.offset.x + pos.x, rect.offset.y + pos.y, rect.size.x, rect.size.y)
      }
    })
    engine.addSystem(new PostRenderSystem(p(), res))
    engine.addSystem(new   UIRenderSystem(p(), res))

    initNetworking()
  }

  override def render(): Unit = {
    listener.run()
    engine.update(Gdx.graphics.getRawDeltaTime * 1000)

    if (packetQueue.numPackets > 10) {
      while(packetQueue.numPackets > 3) {
        engine.update(Gdx.graphics.getRawDeltaTime * 1000)
      }
    }

    engine.getSystems.asScala
      .filter(_.isInstanceOf[RenderSystem])
      .foreach(_.update(Gdx.graphics.getRawDeltaTime * 1000))
  }

  override def resize(width: Int, height: Int) = {
    viewport.update(width, height)
    engine.getSystem(classOf[UIRenderSystem]).resize(width, height)
  }

  def initNetworking(): Unit = {
    KryoRegistrar.register(client.getKryo)
    client.addListener(listener)
    client.start()
    try {
//      client.connect(5000, "10.106.16.95", 54555, 54777)
      client.connect(5000, "127.0.0.1", 54555, 54777)
      println(client.getRemoteAddressUDP.getPort)
    } catch {
      case ioe: IOException => println(ioe)
      case e: Exception => println(e)
    }
  }
}
