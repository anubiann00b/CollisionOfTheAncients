package me.shreyasr.cota.network

import me.shreyasr.cota.component.InputDataComponent

class PacketToClient(val entityData: Array[(Int, InputDataComponent)],
                     val id: Long, val time: Long = System.currentTimeMillis())
  extends Ordered[PacketToClient] {

  override def toString: String =
    s"PacketToClient[$id ${entityData mkString ","}]"

  override def compare(that: PacketToClient): Int = {
    Ordering.Long.compare(that.id, id)
  }
}