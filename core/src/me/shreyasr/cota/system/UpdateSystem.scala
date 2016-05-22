package me.shreyasr.cota.system

import com.badlogic.ashley.core.{Entity, Family}
import com.badlogic.ashley.systems.IteratingSystem
import me.shreyasr.cota.component.attacks.{AttackSetHolder, BiopunkAttackSet, PsyberpunkAttackSet, SteampunkAttackSet}
import me.shreyasr.cota.component.{InputDataComponent, StateDataComponent}
import me.shreyasr.cota.util.Vec2
import me.shreyasr.cota.{MobaGame, _}

class UpdateSystem(priority: Int, res: MobaGame.BaseRes)
  extends IteratingSystem(Family.all(classOf[StateDataComponent]).get(), priority) {

  val attack = new AttackSetHolder(SteampunkAttackSet)

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
        new Vec2(input.mouseX, input.mouseY).sub(state.pos))
      if (input.e) attack.e(getEngine, entity,
        new Vec2(input.mouseX, input.mouseY).sub(state.pos))
    }
  }
}
