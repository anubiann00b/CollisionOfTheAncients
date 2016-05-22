package me.shreyasr.cota.component.attacks

import com.badlogic.ashley.core.{Engine, Entity}
import me.shreyasr.cota.component.{MeleeAttackComponent, RenderDataComponent, StateDataComponent}
import me.shreyasr.cota.util.asset.Asset
import me.shreyasr.cota._
import me.shreyasr.cota.util.{EntityFactory, Vec2}

object BiopunkAttackSet extends AttackSet {

  override val BASIC_ATTACK_TIME: Int = 7
  override val SPACE_TIME: Int = 20
  override val Q_TIME: Int = 20
  override val SHIFT_TIME: Int = 20
  override val E_TIME: Int = 100

  def basicAttack(engine: Engine, me: Entity, dirVec: Vec2): Boolean = {
    if (me.has[MeleeAttackComponent]) {
      false
    } else {
      val dir = (dirVec.dir % (2 * math.Pi)).toFloat
      val bulletEntity = EntityFactory.createBullet(me.get[StateDataComponent].pos, me.id,
        Asset.PSYBERPUNK_BULLET, 3, 1)
      bulletEntity.get[StateDataComponent].vel.set(Vec2.fromDir(dir)).scale(15)
      bulletEntity.get[RenderDataComponent].rotation = dir.toFloat
      engine.addEntity(bulletEntity)
      true
    }
  }
  def e(engine:Engine, me:Entity, dirVec: Vec2): Boolean = {
    val dir = (dirVec.dir % (2*math.Pi)).toFloat
    val bulletEntity = EntityFactory.createBullet(me.get[StateDataComponent].pos, me.id,
      Asset.BIOPUNK_HOOK, 8, 8)
    bulletEntity.get[StateDataComponent].vel.set(Vec2.fromDir(dir)).scale(7)
    bulletEntity.get[RenderDataComponent].rotation = dir.toFloat - 90
    engine.addEntity(bulletEntity)
    true
  }

}
