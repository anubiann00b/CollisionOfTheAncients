package me.shreyasr.cota.system

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.{Pixmap, Texture}
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.{Image, Skin}
import com.badlogic.gdx.utils.viewport.ExtendViewport
import me.shreyasr.cota.MobaGame.RenderingRes
import me.shreyasr.cota.util.MinimapDrawable

class UIRenderSystem(priority: Int, res: RenderingRes) extends EntitySystem(priority) {

  val skin = new Skin
  skin.addRegions(new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas")))
  skin.add("default-font", res.font)
  skin.load(Gdx.files.internal("ui/uiskin.json"))

  val viewport = new ExtendViewport(
    res.viewport.getMinWorldWidth, res.viewport.getMinWorldHeight,
    res.viewport.getMaxWorldWidth, res.viewport.getMaxWorldHeight)
  val stage = new Stage(viewport)

  val backgroundTex = {
    val backgroundPix = new Pixmap(1, 1, Pixmap.Format.RGBA8888)
    backgroundPix.setColor(0, 0, 0, 0.4f)
    backgroundPix.fill()
    new Texture(backgroundPix)
  }

  val minimap = new Image(new MinimapDrawable(res.engine, backgroundTex))

  stage.addActor(minimap)

  override def update(deltaTime: Float): Unit = {
    stage.act(deltaTime)
    viewport.getCamera.position.set(stage.getWidth / 2, stage.getHeight / 2, 0)
    stage.getViewport.apply()
    viewport.getCamera.update()
    stage.draw()
  }

  def resize(width: Int, height: Int) {
    stage.getViewport.update(width, height)

    val minimapHeight = (stage.getHeight / 3).toInt
    val minimapWidth = minimapHeight

    minimap.setPosition(stage.getWidth - minimapWidth, stage.getHeight - minimapHeight)
    minimap.setSize(minimapWidth, minimapHeight)
  }
}