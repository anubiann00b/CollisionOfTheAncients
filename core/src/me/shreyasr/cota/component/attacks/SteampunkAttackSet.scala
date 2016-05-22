package me.shreyasr.cota.component.attacks

import com.badlogic.ashley.core.{Engine, Entity}
import me.shreyasr.cota._
import me.shreyasr.cota.component.{RenderDataComponent, StateDataComponent}
import me.shreyasr.cota.util.asset.Asset
import me.shreyasr.cota.util.{EntityFactory, Vec2}

object SteampunkAttackSet extends AttackSet {

  override var BASIC_ATTACK_TIME: Int = 10
  override var SPACE_TIME: Int = 20
  override var Q_TIME: Int = 20
  override var SHIFT_TIME: Int = 20
  override var E_TIME: Int = 20

  var SHOTGUN_BULLETS = 5
  var SHOTGUN_ARC = math.toRadians(30).toFloat

  def basicAttack(engine: Engine, me: Entity, dirVec: Vec2): Boolean = {
    val baseDir = (dirVec.dir % (2*math.Pi)).toFloat - SHOTGUN_ARC/2
    (0 until SHOTGUN_BULLETS).foreach(i => {
      val dir = baseDir + i * SHOTGUN_ARC/SHOTGUN_BULLETS
      val bulletEntity = EntityFactory.createBullet(me.get[StateDataComponent].pos, me.id,
        Asset.STEAMPUNK_SHOTGUN_BULLET, 7, 3, 2)
      bulletEntity.get[StateDataComponent].vel.set(Vec2.fromDir(dir)).scale(2)
      bulletEntity.get[RenderDataComponent].rotation = dir.toFloat
      engine.addEntity(bulletEntity)
    })
    true
  }
  def e (engine: Engine, me: Entity, dirVec: Vec2): Boolean = {
    BASIC_ATTACK_TIME = 1
    SHOTGUN_BULLETS = 15
    false
  }
}
