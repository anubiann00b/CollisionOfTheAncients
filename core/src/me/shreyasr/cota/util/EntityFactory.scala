package me.shreyasr.cota.util

import com.badlogic.ashley.core.Entity
import me.shreyasr.cota.component._
import me.shreyasr.cota.util.asset.Asset

object EntityFactory {

  def createRenderablePlayer(id: Int = IdComponent.randomId()) =
    createPlayer(id)
      .add(new RenderDataComponent(Asset.STEAMPUNK, 8, 8, 4f))

  def createPlayer(id: Int) = new Entity()
    .add(new TypeComponent(TypeComponent.Player))
    .add(new IdComponent(id))
    .add(new StateDataComponent(new Rectangle(0, 0, 32, 32)))
    .add(new InputDataComponent)
}
