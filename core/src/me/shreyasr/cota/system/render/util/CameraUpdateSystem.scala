package me.shreyasr.cota.system.render.util

import com.badlogic.ashley.core.EntitySystem
import me.shreyasr.cota.MobaGame.RenderingRes
import me.shreyasr.cota._
import me.shreyasr.cota.component.StateDataComponent

class CameraUpdateSystem(priority: Int, res: RenderingRes) extends EntitySystem(priority) {

  override def update(deltaTime: Float) {
    val state = res.player.get[StateDataComponent]
    res.camera.position.set(
      state.pos.x.clamp(
        res.viewport.getWorldWidth / 2,
        MobaGame.WORLD_WIDTH - res.viewport.getWorldWidth / 2),
      state.pos.y.clamp(
        res.viewport.getWorldHeight / 2,
        MobaGame.WORLD_HEIGHT - res.viewport.getWorldHeight / 2),
      0)
    println(res.camera.position)
    res.viewport.apply()
    res.camera.update()
  }
}
