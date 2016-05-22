package me.shreyasr.cota.system

import com.badlogic.ashley.core.{Entity, Family}
import com.badlogic.ashley.systems.IteratingSystem
import me.shreyasr.cota.MobaGame
import me.shreyasr.cota.component.{RenderDataComponent, StateDataComponent}
import me.shreyasr.cota._
import me.shreyasr.cota.component.TypeComponent.Player

class RenderDataUpdateSystem(priority: Int, res: MobaGame.ClientRes)
  extends IteratingSystem(Family.all(classOf[StateDataComponent], classOf[RenderDataComponent]).get(), priority) {

  override def processEntity(entity: Entity, delta: Float): Unit = {
    val state = entity.get[StateDataComponent]
    val render = entity.get[RenderDataComponent]

    render.pos.x = state.pos.x
    render.pos.y = state.pos.y

    if (entity.is[Player]) {
      val vel = state.pos.copy.sub(state.lastPos)
      val size = 8
      var frameOffset = 0

      def updateAnimWithDir(dir: Int) = {
        if (dir == -1) {
          render.srcY = 8
        }
        if (render.currentDir == dir && dir != -1) {
          render.currentFrameTime += delta
        } else {
          render.currentDir = dir
          render.currentFrameTime = 0
          render.currentFrame = 0
        }
      }

      render.srcWidth = size
      render.srcHeight = size
      render.flipX = false

      if (vel.isZero) {
        updateAnimWithDir(-1)
      } else if (math.abs(vel.x) > math.abs(vel.y)) { // sides
        render.srcY = 0
        if (vel.x < 0) { // left, 2
          render.flipX = true
          updateAnimWithDir(2)
        } else { // right, 0
          updateAnimWithDir(0)
        }
      } else { // verticals
        frameOffset = 8
        if (vel.y > 0) { // top, 1
          render.srcY = 16
          updateAnimWithDir(1)
        } else { // bottom, 3
          render.srcY = 8
          updateAnimWithDir(3)
        }
      }

      if (render.currentFrameTime > render.currentDuration) {
        render.currentFrameTime -= render.currentDuration
        render.currentFrame += 1
        if (render.currentFrame >= 2) {
          render.currentFrame = 0
        }
      }

      render.srcX = render.currentFrame * render.srcWidth + frameOffset
    }

    state.lastPos.set(state.pos)
  }
}
