package id.my.arieftb.meowvie.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.my.arieftb.meowvie.data.local.db.MeowVieDb
import id.my.arieftb.meowvie.data.local.watch_list.WatchListDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideWatchListDao(@ApplicationContext context: Context): WatchListDao =
        MeowVieDb.getInstance(context).watchListDao
}