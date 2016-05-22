package me.shreyasr.cota.system.render

import me.shreyasr.cota.MobaGame
import me.shreyasr.cota.system.render.util.BasicRenderSystem

class ShapeRenderSystem(priority: Int, res: MobaGame.RenderingRes) extends BasicRenderSystem(priority) {

  override def update(deltaTime: Float) {
    if (res.batch.isDrawing) res.batch.end()
    if (res.shape.isDrawing) res.shape.end()
    res.shape.begin()
    res.shape.setProjectionMatrix(res.camera.combined)
  }
}
