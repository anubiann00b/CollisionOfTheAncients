package me.shreyasr.cota.system

import com.badlogic.ashley.core.EntitySystem
import me.shreyasr.cota.component.attacks.AttackTimer
import me.shreyasr.cota.util.EntityFactory
import me.shreyasr.cota.{MobaGame, _}

class PacketProcessSystem(priority: Int, res: MobaGame.ClientRes) extends EntitySystem(priority) {

  override def update(deltaTime: Float) {
    AttackTimer.increment()
    res.packetQueue.getNextPacket.foreach(_.entityData.foreach {
      case (id, input) => {
        getEngine.getById(id) match {
          case Some(entity) => entity.add(input)
          case None => getEngine.addEntity(EntityFactory.createRenderablePlayer(id, input))
        }
      }
    })
  }
}