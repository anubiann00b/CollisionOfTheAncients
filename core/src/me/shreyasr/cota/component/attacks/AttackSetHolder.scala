package me.shreyasr.cota.component.attacks

import com.badlogic.ashley.core.{Engine, Entity}
import me.shreyasr.cota.util.Vec2

class AttackSetHolder(var attackSet: AttackSet) {

  var basicAttackTimer = new AttackTimer(attackSet.BASIC_ATTACK_TIME)
  val qTimer = new AttackTimer(attackSet.Q_TIME)
  val eTimer = new AttackTimer(attackSet.E_TIME)
  val spaceTimer = new AttackTimer(attackSet.SPACE_TIME)
  val shiftTimer = new AttackTimer(attackSet.SHIFT_TIME)

  def basicAttack(engine: Engine, me: Entity, dirVec: Vec2): Unit = {
    if (basicAttackTimer.canAttack) {
      val madeAttack = attackSet.basicAttack(engine, me, dirVec)
      if (!madeAttack) basicAttackTimer.reset()
    }
  }
  def e(engine: Engine, me: Entity, dirVec: Vec2): Unit = {
    if (eTimer.canAttack) {
      val madeAttack = attackSet.e(engine, me, dirVec)
      basicAttackTimer = new AttackTimer(attackSet.BASIC_ATTACK_TIME)
      if (!madeAttack) eTimer.reset()
    }
  }
}
