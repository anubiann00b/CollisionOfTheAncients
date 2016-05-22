package me.shreyasr.cota.network

import me.shreyasr.cota.component.StateDataComponent

class ResyncPacketToClient(val entityData: Array[(Int, StateDataComponent)], val id: Int) {

  override def toString: String =
    s"ResyncPacketToClient[$id ${entityData mkString ","}]"
}
