package me.shreyasr.cota.system

import com.badlogic.ashley.core.{Entity, Family}
import com.badlogic.ashley.systems.IteratingSystem
import me.shreyasr.cota.MobaGame
import me.shreyasr.cota.component.{RenderDataComponent, StateDataComponent}
import me.shreyasr.cota._

class RenderDataUpdateSystem(priority: Int, res: MobaGame.ClientRes)
  extends IteratingSystem(Family.all(classOf[StateDataComponent], classOf[RenderDataComponent]).get(), priority) {

  override def processEntity(entity: Entity, deltaTime: Float): Unit = {
    val state = entity.get[StateDataComponent]
    val render = entity.get[RenderDataComponent]

    render.pos.x = state.pos.x
    render.pos.y = state.pos.y
  }
}
