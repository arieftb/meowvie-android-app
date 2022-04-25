package id.my.arieftb.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import id.my.arieftb.core.data.local.watch_list.WatchListDao
import id.my.arieftb.core.data.model.entity.WatchListEntity

@Database(
    entities = [WatchListEntity::class],
    version = 6,
    exportSchema = false
)
abstract class MeowVieDb : RoomDatabase() {
    abstract fun watchListDao(): WatchListDao
}