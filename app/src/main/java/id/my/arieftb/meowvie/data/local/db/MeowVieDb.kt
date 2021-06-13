package id.my.arieftb.meowvie.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.my.arieftb.meowvie.data.constant.DataConstant
import id.my.arieftb.meowvie.data.local.watch_list.WatchListDao
import id.my.arieftb.meowvie.data.model.entity.WatchListEntity

@Database(
    entities = [WatchListEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MeowVieDb : RoomDatabase() {
    abstract val watchListDao: WatchListDao

    companion object {
        @Volatile
        private var INSTANCE: MeowVieDb? = null

        fun getInstance(context: Context): MeowVieDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MeowVieDb::class.java,
                        DataConstant.DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}