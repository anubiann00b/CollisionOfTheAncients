package me.shreyasr.cota.system

import com.badlogic.ashley.core.{Entity, Family}
import com.badlogic.ashley.systems.IteratingSystem
import me.shreyasr.cota.component.attacks.{AttackSetHolder, PsyberpunkAttackSet}
import me.shreyasr.cota.component.{InputDataComponent, StateDataComponent}
import me.shreyasr.cota.util.Vec2
import me.shreyasr.cota.{MobaGame, _}

class UpdateSystem(priority: Int, res: MobaGame.BaseRes, unproject: (Float, Float) => Vec2)
  extends IteratingSystem(Family.all(classOf[StateDataComponent]).get(), priority) {

  val attack = new AttackSetHolder(PsyberpunkAttackSet)

  override def processEntity(entity: Entity, delta: Float) = {
    val inputOpt = entity.getOpt[InputDataComponent]
    val state = entity.get[StateDataComponent]

    if (inputOpt.isDefined) {
      val input = inputOpt.get
      state.vel.set(new Vec2(0, 0))
      if (input.w) state.vel.y = 2
      if (input.s) state.vel.y = -2

      if (input.d) state.vel.x = 2
      if (input.a) state.vel.x = -2

      if (input.leftClick) attack.basicAttack(getEngine, entity,
        unproject(input.mouseX, input.mouseY).sub(state.pos))
    }
  }
}
