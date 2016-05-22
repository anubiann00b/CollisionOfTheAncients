package me.shreyasr.cota.system

import com.badlogic.ashley.core.{Entity, Family}
import com.badlogic.ashley.systems.SortedIteratingSystem
import me.shreyasr.cota._
import me.shreyasr.cota.component.StateDataComponent
import me.shreyasr.cota.component.TypeComponent.Bullet
import me.shreyasr.cota.util.EntityIdComparator

import scala.collection.JavaConverters._

class CollisionSystem(priority: Int)
  extends SortedIteratingSystem(Family.all(classOf[StateDataComponent]).get, EntityIdComparator, priority) {

  def processEntity(me: Entity, deltaTime: Float) {
    if (me.is[Bullet]) return
    getEntities.asScala
      .filterNot(_ == me)
      .foreach(other => {
        val otherState = other.get[StateDataComponent]
        val myState = me.get[StateDataComponent]
        if (otherState.rect.isIn(myState.rect, myState.pos, otherState.pos)) {
//          println("lol collision")
//          other.get[RenderDataComponent].color = Color.RED
//          me.get[RenderDataComponent].color = Color.RED
        }
      })
  }
}
