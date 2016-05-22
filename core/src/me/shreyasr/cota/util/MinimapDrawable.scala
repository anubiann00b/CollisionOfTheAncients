package me.shreyasr.cota.util

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.{Color, Pixmap, Texture}
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable
import me.shreyasr.cota._
import me.shreyasr.cota.component.TypeComponent.{Bullet, GameEntity}
import me.shreyasr.cota.component.{StateDataComponent, TypeComponent}

import scala.collection.JavaConverters._

class MinimapDrawable(engine: Engine, background: Texture) extends BaseDrawable {

  val dot = new Texture({
    val pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888)
    pix.setColor(1, 1, 1, 1)
    pix.fill()
    pix
  })

  override def draw(batch: Batch, x: Float, y: Float, width: Float, height: Float) {
    batch.draw(background, x, y, width, height)
    val dotSize: Int = (height / 24).toInt

    engine.getEntities.asScala
      .filter(_.is[GameEntity])
      .filterNot(_.is[Bullet])
      .foreach(entity => {
        val pos = entity.get[StateDataComponent].pos
        val scaledX = x + pos.x / MobaGame.WORLD_WIDTH * (width - dotSize)
        val scaledY = y + pos.y / MobaGame.WORLD_HEIGHT * (height - dotSize)
        entity.get[TypeComponent].get match {
          case TypeComponent.Player => batch.setColor(Color.GREEN)
          case TypeComponent.Melee => batch.setColor(Color.BLUE)
        }
        batch.draw(dot, scaledX, scaledY, dotSize, dotSize)
      })
  }
}
