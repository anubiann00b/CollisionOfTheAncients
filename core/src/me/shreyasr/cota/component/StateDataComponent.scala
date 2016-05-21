package me.shreyasr.cota.component

import com.badlogic.ashley.core.Component
import com.esotericsoftware.kryo.io.{Input, Output}
import com.esotericsoftware.kryo.{Kryo, Serializer}
import me.shreyasr.cota.util.Vec2

class StateDataComponent(val pos: Vec2 = new Vec2(0, 0))
  extends Component {

  override def toString: String = s"State[$pos]"
}

object StateDataComponent {

  class StateDataComponentSerializer extends Serializer[StateDataComponent] {
    override def write(kryo: Kryo, output: Output, component: StateDataComponent): Unit = {
      kryo.writeObject(output, component.pos)
    }

    override def read(kryo: Kryo, input: Input, cls: Class[StateDataComponent]): StateDataComponent = {
      val pos = kryo.readObject(input, classOf[Vec2])
      val component = new StateDataComponent(pos)
      component
    }
  }
}