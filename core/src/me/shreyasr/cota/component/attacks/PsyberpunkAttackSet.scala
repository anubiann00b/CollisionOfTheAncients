package me.shreyasr.cota.component.attacks

import com.badlogic.ashley.core.{Engine, Entity}
import me.shreyasr.cota.component.{RenderDataComponent, StateDataComponent}
import me.shreyasr.cota.util.asset.Asset
import me.shreyasr.cota._
import me.shreyasr.cota.util.{EntityFactory, Vec2}

object PsyberpunkAttackSet extends AttackSet {

  override var BASIC_ATTACK_TIME: Int = 30
  override var SPACE_TIME: Int = 20
  override var Q_TIME: Int = 20
  override var SHIFT_TIME: Int = 20
  override var E_TIME: Int = 20

  def basicAttack(engine: Engine, me: Entity, dirVec: Vec2): Boolean = {
    val dir = (dirVec.dir % (2*math.Pi)).toFloat
    val bulletEntity = EntityFactory.createBullet(me.get[StateDataComponent].pos, me.id,
      Asset.PSYBERPUNK_BULLET, 3, 1, 1)
    bulletEntity.get[StateDataComponent].vel.set(Vec2.fromDir(dir)).scale(2)
    bulletEntity.get[RenderDataComponent].rotation = dir.toFloat
    engine.addEntity(bulletEntity)
    true
  }

  override def e(engine: Engine, me: Entity, dirVec: Vec2): Boolean = {
    false
  }
}
