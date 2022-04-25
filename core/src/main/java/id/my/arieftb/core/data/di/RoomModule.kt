package id.my.arieftb.core.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.my.arieftb.core.data.constant.DataConstant
import id.my.arieftb.core.data.local.db.MeowVieDb
import id.my.arieftb.core.data.local.watch_list.WatchListDao
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    fun provideWatchListDao(db: MeowVieDb): WatchListDao {
        return db.watchListDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MeowVieDb {
        return Room.databaseBuilder(context, MeowVieDb::class.java, DataConstant.DB_NAME)
            .fallbackToDestructiveMigration()
            .openHelperFactory(SupportFactory(SQLiteDatabase.getBytes("meowvie".toCharArray())))
            .build()
    }
}