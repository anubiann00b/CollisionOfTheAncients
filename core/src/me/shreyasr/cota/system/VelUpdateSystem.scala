package me.shreyasr.cota.system

import com.badlogic.ashley.core.{Entity, Family}
import com.badlogic.ashley.systems.IteratingSystem
import me.shreyasr.cota.{MobaGame, _}
import me.shreyasr.cota.component.StateDataComponent

class VelUpdateSystem(priority: Int)
  extends IteratingSystem(Family.all(classOf[StateDataComponent]).get(), priority) {

  override def processEntity(entity: Entity, delta: Float) = {
    val state = entity.get[StateDataComponent]
    state.pos.add(state.vel)
  }
}
