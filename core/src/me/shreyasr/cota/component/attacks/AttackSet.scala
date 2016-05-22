package me.shreyasr.cota.component.attacks

import com.badlogic.ashley.core.{Engine, Entity}
import me.shreyasr.cota.util.Vec2

trait AttackSet {

  var BASIC_ATTACK_TIME: Int
  var Q_TIME: Int
  var E_TIME: Int
  var SPACE_TIME: Int
  var SHIFT_TIME: Int

  def basicAttack(engine: Engine, me: Entity, dirVec: Vec2): Boolean
  def e(engine: Engine, me: Entity, dirVec: Vec2): Boolean
}
