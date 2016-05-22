package me.shreyasr.cota.component

import com.badlogic.ashley.core.Component
import me.shreyasr.cota._

class OwnerIdComponent(val id: Int) extends Component {

  override def equals(o: Any): Boolean = o.isInstanceOf[OwnerIdComponent] &&
    o.asInstanceOf[OwnerIdComponent].id == id
  override def hashCode: Int = id
  override def toString: String = id.display
}
