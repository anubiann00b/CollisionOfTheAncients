package me.shreyasr.cota.system.render

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import me.shreyasr.cota.MobaGame.RenderingRes
import me.shreyasr.cota.util.asset.MapAsset

class MapRenderSystem(priority: Int, res: RenderingRes) extends EntitySystem(priority) {

  val renderer = new OrthogonalTiledMapRenderer(MapAsset.MAP.get, 1f, res.batch)

  override def update(deltaTime: Float): Unit = {
    renderer.setMap(MapAsset.MAP.get)
    res.batch.setColor(Color.WHITE)
    renderer.setView(res.camera)
    renderer.render()
  }
}
