package me.shreyasr.cota

import java.net.BindException

import com.badlogic.gdx.backends.lwjgl.LwjglNativesLoader
import com.esotericsoftware.kryonet.{Connection, KryoSerialization, Listener, Server}
import com.twitter.chill.ScalaKryoInstantiator
import me.shreyasr.cota.component.{InputDataComponent, TypeComponent}
import me.shreyasr.cota.network.{KryoRegistrar, ListQueuedListener, PacketToClient, PacketToServer}
import me.shreyasr.cota.system.UpdateSystem
import me.shreyasr.cota.util.EntityFactory
import org.lwjgl.opengl.Display

import scala.collection.mutable

object ServerMain extends Listener {

  def main(args: Array[String]) {
    LwjglNativesLoader.load()
    ServerMain.run()
  }

  class ServerRes extends MobaGame.BaseRes {
    val server = new Server(8192, 2048, new KryoSerialization(new ScalaKryoInstantiator().newKryo()))
    val listener = new ListQueuedListener(ServerMain)
    val idMap = new mutable.HashMap[Int, Int]
    val inputs = new mutable.HashMap[Int, InputDataComponent]
  }

  val res = new ServerRes
  import res._

  KryoRegistrar.register(server.getKryo)
  server.start()
  try {
    server.bind(54555, 54777)
  } catch {
    case e: BindException => System.exit(1)
  }
  server.addListener(listener)

  val p = { var i = 0; () => { i += 1; i} }
  engine.addSystem(new UpdateSystem(p(), res))

  var currentPacketId = 0l

  def run() = {
    while (true) {
      listener.run()

      engine.update(1)

      currentPacketId += 1
      server.sendToAllUDP(new PacketToClient(engine.entities
        .filter(_.is[TypeComponent.Player])
        .map(e => (e.id, e.get[InputDataComponent])).toArray, currentPacketId))

      Display.sync(MobaGame.GLOBAL_FPS)
    }
  }

  override def received(conn: Connection, obj: scala.Any) {
    obj match {
      case packet: PacketToServer =>
        idMap += conn.getID -> packet.entityId
        if (engine.getById(packet.entityId).isEmpty) {
          engine.addEntity(EntityFactory.createPlayer(packet.entityId))
        }
        engine.getById(packet.entityId).get.add(packet.inputData)
      case _ =>
    }
  }

  override def disconnected(conn: Connection) {
    val entityOption = idMap.get(conn.getID)
    if (entityOption.isDefined) {
      engine.removeEntity(engine.getById(entityOption.get).get)
      idMap -= conn.getID
    }
  }
}
