package me.shreyasr.cota

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.{BitmapFont, SpriteBatch}
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.{ApplicationAdapter, Gdx}
import me.shreyasr.cota.MobaGame.RenderingRes
import me.shreyasr.cota.system.render.util.RenderSystem
import me.shreyasr.cota.system.render.{MainRenderSystem, PostRenderSystem, PreBatchRenderSystem, PreRenderSystem}
import me.shreyasr.cota.system.{InputSendSystem, RenderDataUpdateSystem, UpdateSystem}
import me.shreyasr.cota.util.{Asset, EntityFactory}

import scala.collection.JavaConverters._

object MobaGame {
  class RenderingRes extends ClientRes {
    val batch = new SpriteBatch
    val shape = new ShapeRenderer
    val assetManager = new AssetManager
    val font = new BitmapFont
  }

  // Includes network stuff
  class ClientRes extends BaseRes {
    val player = EntityFactory.createRenderablePlayer()
  }

  class BaseRes {
    val engine = new Engine
  }
}
class MobaGame extends ApplicationAdapter {

  lazy val res = new RenderingRes()
  import res._

  override def create() {
    Asset.loadAll(assetManager)

    engine.addEntity(player)

    val p = { var i = 0; () => { i += 1; i} }
    engine.addSystem(new InputSendSystem(p(), res))
    engine.addSystem(new UpdateSystem(p(), res))

    engine.addSystem(new RenderDataUpdateSystem(p(), res))
    engine.addSystem(new PreRenderSystem(p()))
    engine.addSystem(new PreBatchRenderSystem(p(), res))
    engine.addSystem(new MainRenderSystem(p(), res))
    engine.addSystem(new PostRenderSystem(p(), res))

    initNetworking()
  }

  override def render(): Unit = {
    // TODO: update packets
    engine.update(1)
    engine.getSystems.asScala
      .filter(_.isInstanceOf[RenderSystem])
      .foreach(_.update(Gdx.graphics.getRawDeltaTime * 1000))
  }

  def initNetworking(): Unit = {

  }
}
