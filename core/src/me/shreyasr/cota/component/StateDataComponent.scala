package me.shreyasr.cota.component

import com.badlogic.ashley.core.Component
import me.shreyasr.cota.util.{Rectangle, Vec2}

class StateDataComponent(val rect:Rectangle, val pos: Vec2 = new Vec2(0, 0)) extends Component {

  val lastPos: Vec2 = new Vec2(0, 0)

  override def toString: String = s"State[$pos]"
}