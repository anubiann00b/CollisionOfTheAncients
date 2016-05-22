package me.shreyasr.cota.network

import me.shreyasr.cota.MobaGame

import scala.collection.mutable

class PacketQueue {

  var queue = new mutable.PriorityQueue[PacketToClient]()

  def numPackets: Int = queue.size

  def addPacket(packet: PacketToClient): Unit = {
    if (!queue.exists(_.id == packet.id)) {
      queue += packet
    }
  }

  def getNextPacket: Option[PacketToClient] = {
    if (queue.size <= MobaGame.FRAME_BUFFER) None
    else Some(queue.dequeue())
  }
}