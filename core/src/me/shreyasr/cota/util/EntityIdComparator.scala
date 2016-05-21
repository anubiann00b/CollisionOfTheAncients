package me.shreyasr.cota.util

import java.util.Comparator

import com.badlogic.ashley.core.Entity

import me.shreyasr.cota._

/**
  * Created by Benjamin on 5/21/2016.
  */
class EntityIdComparator extends Comparator[Entity] {
  override def compare(o1: Entity, o2: Entity): Int = Integer.compare(o1.id, o2.id)
}
