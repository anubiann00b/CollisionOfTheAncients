package me.shreyasr.cota.component

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.{Gdx, Input}

class InputDataComponent(var w: Boolean = false,
                         var a: Boolean = false,
                         var s: Boolean = false,
                         var d: Boolean = false,
                         var q: Boolean = false,
                         var e: Boolean = false,
                         var shift: Boolean = false,
                         var space: Boolean = false,
                         var leftClick: Boolean = false,
                         var rightClick: Boolean = false,
                         var mouseX: Float = 0,
                         var mouseY: Float = 0)
  extends Component {

  def this() = this(false)

  override def toString: String = "Input[" +
    (if (w) "w" else "") + (if (a) "a" else "") + (if (s) "s" else "") + (if (d) "d" else "") + "]"
}

object InputDataComponent {

  def create(mx: Float, my: Float): InputDataComponent = {
    new InputDataComponent(
      Gdx.input.isKeyPressed(Input.Keys.W),
      Gdx.input.isKeyPressed(Input.Keys.A),
      Gdx.input.isKeyPressed(Input.Keys.S),
      Gdx.input.isKeyPressed(Input.Keys.D),
      Gdx.input.isKeyPressed(Input.Keys.Q),
      Gdx.input.isKeyPressed(Input.Keys.E),
      Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT),
      Gdx.input.isKeyPressed(Input.Keys.SPACE),
      Gdx.input.isButtonPressed(Input.Buttons.LEFT),
      Gdx.input.isButtonPressed(Input.Buttons.RIGHT),
      mx, my)
  }
}