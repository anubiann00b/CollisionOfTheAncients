package me.shreyasr.cota.util

import me.shreyasr.cota._

class Vec2(var x: Float, var y: Float) {

  def this() = this(0, 0)

  override def toString: String = s"Vec2[${x.display},${y.display}]"
  def copy: Vec2 = new Vec2(x, y)

  def isZero = x == 0 && y == 0

  def add(that: Vec2) = {
    x += that.x
    y += that.y
    this
  }

  def set(that: Vec2) = {
    x = that.x
    y = that.y
    this
  }

  def sub(that: Vec2) = {
    x -= that.x
    y -= that.y
    this
  }
}
