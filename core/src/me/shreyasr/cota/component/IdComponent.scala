package me.shreyasr.cota.component

import java.util.Random
import me.shreyasr.cota._

import com.badlogic.ashley.core.Component

class IdComponent(val id: Int) extends Component {

  override def equals(o: Any): Boolean = o.isInstanceOf[IdComponent] && o.asInstanceOf[IdComponent].id == id
  override def hashCode: Int = id
  override def toString: String = id.display
}

object IdComponent {
  private val random: Random = new Random

  def randomId(): Int = random.nextInt
  def create() = new IdComponent(randomId())
}
