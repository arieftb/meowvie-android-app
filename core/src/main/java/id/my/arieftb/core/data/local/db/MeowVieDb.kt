package id.my.arieftb.core.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.my.arieftb.core.data.constant.DataConstant
import id.my.arieftb.core.data.local.watch_list.WatchListDao
import id.my.arieftb.core.data.model.entity.WatchListEntity
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Database(
    entities = [WatchListEntity::class],
    version = 5,
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
                val passphrase: ByteArray = SQLiteDatabase.getBytes("meowvie".toCharArray())
                val factory = SupportFactory(passphrase)

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MeowVieDb::class.java,
                        DataConstant.DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .openHelperFactory(factory)
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}