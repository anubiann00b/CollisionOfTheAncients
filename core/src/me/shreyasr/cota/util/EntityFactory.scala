package me.shreyasr.cota.util

import com.badlogic.ashley.core.Entity
import me.shreyasr.cota.component._

object EntityFactory {

  def createRenderablePlayer(id: Int = IdComponent.randomId()) =
    createPlayer(id)
      .add(new RenderDataComponent(Asset.PLAYER, 58, 72, 1f))

  def createPlayer(id: Int) = new Entity()
    .add(new TypeComponent(TypeComponent.Player))
    .add(new IdComponent(id))
    .add(new StateDataComponent)
    .add(new InputDataComponent)
}
