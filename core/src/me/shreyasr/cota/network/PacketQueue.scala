package me.shreyasr.cota.network

import scala.collection.mutable

class PacketQueue {

  var queue = new mutable.PriorityQueue[PacketToClient]()

  def addPacket(packet: PacketToClient): Unit = {
    if (!queue.exists(_.id == packet.id)) {
      queue += packet
    }
  }

  def getNextPacket: Option[PacketToClient] = {
    if (queue.size <= 3) None
    else Some(queue.dequeue())
  }
}