package me.shreyasr.cota.system.render

import com.badlogic.ashley.core.{Entity, Family}
import com.badlogic.gdx.graphics.Texture.TextureFilter
import me.shreyasr.cota.component.RenderDataComponent
import me.shreyasr.cota.system.render.util.IteratingRenderSystem
import me.shreyasr.cota.{MobaGame, _}

class MainRenderSystem(priority: Int, res: MobaGame.RenderingRes)
  extends IteratingRenderSystem(Family.all(classOf[RenderDataComponent]).get(), priority) {

  override def processEntity(entity: Entity, deltaTime: Float) {
    val ttc = entity.get[RenderDataComponent]
    if (!ttc.hide) {
      ttc.asset.get.setFilter(TextureFilter.Nearest, TextureFilter.Nearest)

      res.batch.setColor(ttc.color)
      res.batch.draw(ttc.asset.get,
        ttc.pos.x - ttc.originX, ttc.pos.y - ttc.originY,
        ttc.originX, ttc.originY,
        ttc.screenWidth, ttc.screenHeight,
        1, 1, ttc.rotation,
        ttc.srcX, ttc.srcY, ttc.srcWidth, ttc.srcHeight,
        ttc.flipX, ttc.flipY)
    }
  }
}
