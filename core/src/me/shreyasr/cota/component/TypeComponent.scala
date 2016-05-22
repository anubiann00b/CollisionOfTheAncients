package me.shreyasr.cota.component

import com.badlogic.ashley.core.Component
import me.shreyasr.cota.component.TypeComponent.Type

import scala.reflect.ClassTag

class TypeComponent(t: Type) extends Component {

  def get = t

  def is[T <: Type: ClassTag]: Boolean = {
    implicitly[ClassTag[T]].runtimeClass.isInstance(t)
  }
}

object TypeComponent {

  sealed trait Type
    trait GameEntity extends Type
      trait Player extends GameEntity
        object Player extends Player
      trait Melee extends GameEntity
        object Melee extends Melee
      trait Bullet extends GameEntity
        object Bullet extends Bullet
}
