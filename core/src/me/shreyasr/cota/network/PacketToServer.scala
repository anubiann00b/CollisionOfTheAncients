package me.shreyasr.cota.network

import me.shreyasr.cota.component.InputDataComponent

class PacketToServer(val entityId: Int, val inputData: InputDataComponent) {

  def this() = this(-1, null)

  override def toString: String = s"PacketToServer[$entityId $inputData]"
}
