package me.shreyasr.cota

import java.io.IOException

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.{BitmapFont, SpriteBatch}
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.{ApplicationAdapter, Gdx}
import com.esotericsoftware.kryonet.{Listener, _}
import com.twitter.chill.ScalaKryoInstantiator
import me.shreyasr.cota.MobaGame.RenderingRes
import me.shreyasr.cota.network.{KryoRegistrar, ListQueuedListener, PacketQueue, PacketToClient}
import me.shreyasr.cota.system.render.util.RenderSystem
import me.shreyasr.cota.system.render.{MainRenderSystem, PostRenderSystem, PreBatchRenderSystem, PreRenderSystem}
import me.shreyasr.cota.system.{InputSendSystem, PacketProcessSystem, RenderDataUpdateSystem, UpdateSystem}
import me.shreyasr.cota.util.{Asset, EntityFactory}

import scala.collection.JavaConverters._

object MobaGame {

  val GLOBAL_FPS = 60

  class RenderingRes extends ClientRes {
    val batch = new SpriteBatch
    val shape = new ShapeRenderer
    val assetManager = new AssetManager
    val font = new BitmapFont
  }

  class ClientRes extends BaseRes {
    val player = EntityFactory.createRenderablePlayer()
    val client = new Client(8192, 2048, new KryoSerialization(new ScalaKryoInstantiator().newKryo()))
    val listener = new ListQueuedListener(new Listener {
      override def received(conn: Connection, obj: scala.Any): Unit = {
        obj match {
          case packet: PacketToClient =>
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
    engine.addSystem(new InputSendSystem(p(), res))
    engine.addSystem(new PacketProcessSystem(p(), res))
    engine.addSystem(new UpdateSystem(p(), res))

    engine.addSystem(new RenderDataUpdateSystem(p(), res))
    engine.addSystem(new PreRenderSystem(p()))
    engine.addSystem(new PreBatchRenderSystem(p(), res))
    engine.addSystem(new MainRenderSystem(p(), res))
    engine.addSystem(new PostRenderSystem(p(), res))

    initNetworking()
  }

  override def render(): Unit = {
    listener.run()
    // TODO: update packets
    engine.update(Gdx.graphics.getRawDeltaTime * 1000)
    engine.getSystems.asScala
      .filter(_.isInstanceOf[RenderSystem])
      .foreach(_.update(Gdx.graphics.getRawDeltaTime * 1000))
  }

  def initNetworking(): Unit = {
    KryoRegistrar.register(client.getKryo)
    client.addListener(listener)
    client.start()
    try {
      client.connect(5000, "127.0.0.1", 54555, 54777)
      println(client.getRemoteAddressUDP.getPort)
    } catch {
      case ioe: IOException => println(ioe)
      case e: Exception => println(e)
    }
  }
}
