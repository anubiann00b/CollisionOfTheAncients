package me.shreyasr.cota.component

import com.badlogic.ashley.core.Component

/**
  * Created by Benjamin on 5/22/2016.
  */
class HealthComponent (var health:Int) extends Component{
  def takeDamage(dam:Int) : Int = {
    health = health - dam
    health
  }
}
