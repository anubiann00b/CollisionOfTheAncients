package me.shreyasr.cota.util.asset

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import enumeratum.values.{ShortEnum, ShortEnumEntry}

sealed abstract class Asset(val value: Short, val file: String) extends ShortEnumEntry {

  def get: Texture = Asset.assetManager.get[Texture](file)
}

object Asset extends ShortEnum[Asset] {

  var assetManager: AssetManager = null

  def loadAll(assetManager: AssetManager) {
    Asset.assetManager = assetManager
    MapAsset.loadAll(assetManager)
    for (asset <- values) {
      assetManager.load(asset.file, classOf[Texture])
    }
    assetManager.finishLoading()
  }

  case object STEAMPUNK extends Asset(1, "sprites/steampunk.png")
  case object STEAMPUNK_SHOTGUN_BULLET extends Asset(2, "sprites/Steampunk_bullet.png")
  case object STEAMPUNK_GRENADE extends Asset(3, "sprites/Steampunk_grenade.png")

  case object PSYBERPUNK extends Asset(8, "sprites/Psyberpunk.png")
  case object PSYBERPUNK_BULLET extends Asset(9, "sprites/Psyberpunk_bullet.png")

  override def values = findValues
}
