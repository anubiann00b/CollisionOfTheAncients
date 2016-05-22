package me.shreyasr.cota.component.attacks

import com.badlogic.ashley.core.{Engine, Entity}
import me.shreyasr.cota._
import me.shreyasr.cota.component.{RenderDataComponent, StateDataComponent}
import me.shreyasr.cota.util.{EntityFactory, Vec2}

object SteampunkAttackSet {

  val SHOTGUN_BULLETS = 5
  val SHOTGUN_ARC = math.toRadians(30).toFloat

  val basicAttackTimer = new AttackTimer(20)

  def basicAttack(engine: Engine, me: Entity, dirVec: Vec2): Unit = {
    if (!basicAttackTimer.canAttack) return

    val baseDir = (dirVec.dir % (2*math.Pi)).toFloat - SHOTGUN_ARC/2
    (0 until SHOTGUN_BULLETS).foreach(i => {
      val dir = baseDir + i * SHOTGUN_ARC/SHOTGUN_BULLETS
      val bulletEntity = EntityFactory.createBullet(me.get[StateDataComponent].pos)
      bulletEntity.get[StateDataComponent].vel.set(Vec2.fromDir(dir)).scale(3)
      bulletEntity.get[RenderDataComponent].rotation = dir.toFloat
      engine.addEntity(bulletEntity)
    })
  }
}
