package me.shreyasr.cota.system.render

import com.badlogic.gdx.graphics.Color
import me.shreyasr.cota.MobaGame
import me.shreyasr.cota.system.render.util.BasicRenderSystem

class PreBatchRenderSystem(priority: Int, res: MobaGame.RenderingRes) extends BasicRenderSystem(priority) {

  override def update(deltaTime: Float) {
    if (res.shape.isDrawing) res.shape.end()
    if (res.batch.isDrawing) res.batch.end()
    res.batch.begin()
    res.batch.setColor(Color.WHITE)
    res.batch.setProjectionMatrix(res.camera.combined)
  }
}
