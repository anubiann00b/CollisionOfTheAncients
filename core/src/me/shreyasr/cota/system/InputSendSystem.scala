package me.shreyasr.cota.system

import com.badlogic.ashley.core.EntitySystem
import me.shreyasr.cota.MobaGame
import me.shreyasr.cota.component.InputDataComponent
import me.shreyasr.cota.network.PacketToServer
import me.shreyasr.cota._

class InputSendSystem(priority: Int, res: MobaGame.ClientRes) extends EntitySystem(priority) {

  override def update(deltaTime: Float) {
    res.client.sendUDP(new PacketToServer(res.player.id, InputDataComponent.create))
  }
}
