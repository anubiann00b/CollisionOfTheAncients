package me.shreyasr.cota.component

import com.badlogic.ashley.core.Component
import me.shreyasr.cota.component.MeleeAttackComponent.TargettingMechanism

class MeleeAttackComponent(val delay: Int,
                           val targettingMechanism: TargettingMechanism,
                           val lockMovement: Boolean) extends Component {



}

object MeleeAttackComponent {

  trait TargettingMechanism {
    def doesHit(): Boolean
    def onHit()
  }

//  class
}