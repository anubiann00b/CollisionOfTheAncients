package me.shreyasr.cota.component

import me.shreyasr.cota.component.MeleeAttackComponent.TargettingMechanism

class MeleeAttackComponent(val delay: Int,
                           val targettingMechanism: TargettingMechanism,
                           val lockMovement: Boolean) {


}

object MeleeAttackComponent {

  trait TargettingMechanism {
    def doesHit()
    def onHit()
  }

//  class
}