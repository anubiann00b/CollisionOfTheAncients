package me.shreyasr.cota.system

import com.badlogic.ashley.core.EntitySystem
import me.shreyasr.cota.MobaGame
import me.shreyasr.cota.component.InputDataComponent
import me.shreyasr.cota.network.{PacketToClient, PacketToServer}
import me.shreyasr.cota._

class InputSendSystem(priority: Int, res: MobaGame.ClientRes) extends EntitySystem(priority) {

  var id = 0 // only if there is no server

  override def update(deltaTime: Float) {
    if (res.client.isConnected) {
      res.client.sendUDP(new PacketToServer(res.player.id, InputDataComponent.create))
    } else {
      id += 1
      res.packetQueue.addPacket(new PacketToClient(Array((res.player.id, InputDataComponent.create)), id))
    }
  }
}
