package me.shreyasr.cota.component.attacks

import com.badlogic.ashley.core.{Engine, Entity}
import me.shreyasr.cota.util.Vec2

trait AttackSet {

  val BASIC_ATTACK_TIME: Int
  val Q_TIME: Int
  val E_TIME: Int
  val SPACE_TIME: Int
  val SHIFT_TIME: Int

  def basicAttack(engine: Engine, me: Entity, dirVec: Vec2)
}
