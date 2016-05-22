package me.shreyasr.cota.util.asset

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.maps.tiled.{TiledMap, TmxMapLoader}
import enumeratum.values.{ShortEnum, ShortEnumEntry}

sealed abstract class MapAsset(val value: Short, val file: String) extends ShortEnumEntry {

  def get: TiledMap = Asset.assetManager.get[TiledMap](file)
}

object MapAsset extends ShortEnum[MapAsset] {

  private [asset] def loadAll(assetManager: AssetManager) {
    assetManager.setLoader(classOf[TiledMap], new TmxMapLoader(new InternalFileHandleResolver()))
    for (asset <- values) {
      assetManager.load(asset.file, classOf[TiledMap])
    }
  }

  case object MAP extends MapAsset(1, "map/world_simple_demo.tmx")

  override def values = findValues
}
