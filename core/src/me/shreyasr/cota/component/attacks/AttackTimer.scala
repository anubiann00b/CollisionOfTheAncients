package me.shreyasr.cota.component.attacks

class AttackTimer(private val frames: Int) {

  private var lastFrame = 0

  def canAttack: Boolean = {
    if (AttackTimer.currentFrame - lastFrame >= frames) {
      lastFrame = AttackTimer.currentFrame
      true
    } else false
  }
}
object AttackTimer {
  def increment() = currentFrame += 1
  var currentFrame = 0
}