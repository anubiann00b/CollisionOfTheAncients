package me.shreyasr.cota.component.attacks

import com.badlogic.ashley.core.{Engine, Entity}
import me.shreyasr.cota.component.{RenderDataComponent, StateDataComponent}
import me.shreyasr.cota.util.asset.Asset
import me.shreyasr.cota._
import me.shreyasr.cota.util.{EntityFactory, Vec2}

object BiopunkAttackSet extends AttackSet {

  override var BASIC_ATTACK_TIME: Int = 7
  override var SPACE_TIME: Int = 20
  override var Q_TIME: Int = 20
  override var SHIFT_TIME: Int = 20
  override var E_TIME: Int = 100

  def basicAttack(engine: Engine, me: Entity, dirVec: Vec2): Boolean = {
    val dir = (dirVec.dir % (2*math.Pi)).toFloat
    val bulletEntity = EntityFactory.createMelee(me.get[StateDataComponent].pos, me.id,
      Asset.BIOPUNK_ATTACK, 14, 7, 1, 9)
    bulletEntity.get[RenderDataComponent].rotation = dir.toFloat -  (math.Pi/2).toFloat
    bulletEntity.get[RenderDataComponent].pos.add(dirVec.scale(10))
    bulletEntity.get[StateDataComponent].pos.add(dirVec.norm().scale(40))
    engine.addEntity(bulletEntity)
    true
  }
  def e(engine:Engine, me:Entity, dirVec: Vec2): Boolean = {
    val dir = (dirVec.dir % (2*math.Pi)).toFloat
    val bulletEntity = EntityFactory.createBullet(me.get[StateDataComponent].pos, me.id,
      Asset.BIOPUNK_HOOK, 8, 8, 5 )
    bulletEntity.get[StateDataComponent].vel.set(Vec2.fromDir(dir)).scale(2)
    bulletEntity.get[RenderDataComponent].rotation = dir.toFloat - (math.Pi/2).toFloat
    engine.addEntity(bulletEntity)
    true
  }

}
