package me.shreyasr.cota.system

import com.badlogic.ashley.core.{Entity, Family}
import com.badlogic.ashley.systems.IteratingSystem
import me.shreyasr.cota.{MobaGame, _}
import me.shreyasr.cota.component.{InputDataComponent, StateDataComponent}

class UpdateSystem(priority: Int, res: MobaGame.BaseRes)
  extends IteratingSystem(Family.all(classOf[StateDataComponent]).get(), priority) {

  override def processEntity(entity: Entity, delta: Float) = {
    val inputOpt = entity.getOpt[InputDataComponent]
    val state = entity.get[StateDataComponent]

    if (inputOpt.isDefined) {
      val input = inputOpt.get
      if (input.w) state.pos.y += 2
      if (input.s) state.pos.y -= 2

      if (input.d) state.pos.x += 2
      if (input.a) state.pos.x -= 2
    }
  }
}
