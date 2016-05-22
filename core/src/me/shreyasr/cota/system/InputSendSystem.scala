package me.shreyasr.cota.system

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.Gdx
import me.shreyasr.cota.MobaGame
import me.shreyasr.cota.component.InputDataComponent
import me.shreyasr.cota.network.{PacketToClient, PacketToServer}
import me.shreyasr.cota._
import me.shreyasr.cota.util.Vec2

class InputSendSystem(priority: Int, res: MobaGame.ClientRes, unproject: (Float, Float) => Vec2) extends EntitySystem(priority) {

  var id = 0 // only if there is no server

  override def update(deltaTime: Float) {
    val mouse = unproject(Gdx.input.getX, Gdx.input.getY)
    if (res.client.isConnected) {
      res.client.sendUDP(new PacketToServer(res.player.id, InputDataComponent.create(mouse.x, mouse.y)))
    } else {
      id += 1
      res.packetQueue.addPacket(new PacketToClient(Array((res.player.id, InputDataComponent.create(mouse.x, mouse.y))), id))
    }
  }
}
