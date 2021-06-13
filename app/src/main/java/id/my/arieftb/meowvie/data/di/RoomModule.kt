package id.my.arieftb.meowvie.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.my.arieftb.meowvie.data.local.db.MeowVieDb
import id.my.arieftb.meowvie.data.local.movie.MovieDao
import id.my.arieftb.meowvie.data.local.tv_show.TvShowDao
import id.my.arieftb.meowvie.data.local.watch_list.WatchListDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideMovieDao(@ApplicationContext context: Context): MovieDao =
        MeowVieDb.getInstance(context).movieDao

    @Provides
    @Singleton
    fun provideTvShowDao(@ApplicationContext context: Context): TvShowDao =
        MeowVieDb.getInstance(context).tvShowDao

    @Provides
    @Singleton
    fun provideWatchListDao(@ApplicationContext context: Context): WatchListDao =
        MeowVieDb.getInstance(context).watchListDao
}