package id.my.arieftb.meowvie.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.my.arieftb.meowvie.data.local.db.MeowVieDb
import id.my.arieftb.meowvie.data.local.movie.MovieDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideMovieDao(@ApplicationContext context: Context): MovieDao =
        MeowVieDb.getInstance(context).movieDao
}