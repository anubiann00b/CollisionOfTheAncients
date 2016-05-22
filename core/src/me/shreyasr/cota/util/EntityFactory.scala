package me.shreyasr.cota.util

import com.badlogic.ashley.core.Entity
import me.shreyasr.cota.component._
import me.shreyasr.cota.util.asset.Asset

object EntityFactory {

  def createBullet(pos: Vec2, owner: Int) = new Entity()
    .add(new IdComponent(-1))
    .add(new OwnerIdComponent(owner))
    .add(new TypeComponent(TypeComponent.Bullet))
    .add(new StateDataComponent(pos.copy(), new Rectangle(-4, -4, 8, 8)))
    .add(new RenderDataComponent(Asset.STEAMPUNK_SHOTGUN_BULLET, 7, 3, 4f))

  def createRenderablePlayer(id: Int = IdComponent.randomId(),
                             input: InputDataComponent = new InputDataComponent()) =
    createPlayer(id)
      .add(new RenderDataComponent(Asset.STEAMPUNK, 8, 8, 4f))

  def createPlayer(id: Int) = new Entity()
    .add(new TypeComponent(TypeComponent.Player))
    .add(new IdComponent(id))
    .add(new StateDataComponent(new Vec2(0, 0), new Rectangle(-16, -16, 32, 32)))
    .add(new InputDataComponent)
}
