package me.shreyasr.cota.system.render.util

import me.shreyasr.cota.MobaGame

class ProjectionSystem(priority: Int, res: MobaGame.RenderingRes) extends BasicRenderSystem(priority) {

  override def update(deltaTime: Float) {
    res.batch.setProjectionMatrix(res.camera.combined)
  }
}
