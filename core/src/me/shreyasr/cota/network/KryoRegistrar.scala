package me.shreyasr.cota.network

import com.esotericsoftware.kryo.Kryo
import me.shreyasr.cota.component.{InputDataComponent, StateDataComponent, TypeComponent}
import me.shreyasr.cota.util.Vec2

import scala.reflect._

object KryoRegistrar {

  def register(kryo: Kryo) = {
    kryo.registerType[PacketToClient]
    kryo.registerType[PacketToServer]

    kryo.registerType[StateDataComponent]
    kryo.registerType[InputDataComponent]
    kryo.registerType[TypeComponent]

    kryo.registerType[Vec2]

    kryo.register(TypeComponent.Player.getClass)
  }

  implicit class KryoImprovements(val kryo: Kryo) {
    def registerType[T: ClassTag] = {
      kryo.register(classTag[T].runtimeClass)
    }
  }
}