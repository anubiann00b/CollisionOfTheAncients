package me.shreyasr.cota.system.render

import me.shreyasr.cota.MobaGame
import me.shreyasr.cota.system.render.util.BasicRenderSystem

class PostRenderSystem(priority: Int, res: MobaGame.RenderingRes) extends BasicRenderSystem(priority) {

  override def update(deltaTime: Float) {
    if (res.shape.isDrawing) res.shape.end()
    if (res.batch.isDrawing) res.batch.end()
  }
}
