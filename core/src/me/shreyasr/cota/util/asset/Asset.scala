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

  case object STEAMPUNK extends Asset(1, "steampunk.png")
  case object SHOTGUN_BULLET extends Asset(2, "Steampunk_bullet.png")

  override def values = findValues
}
