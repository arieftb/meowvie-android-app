package id.my.arieftb.meowvie.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.my.arieftb.meowvie.data.remote.movie.MovieApiService
import id.my.arieftb.meowvie.data.remote.movie.MovieRemoteDataSource
import id.my.arieftb.meowvie.data.remote.movie.MovieRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(movieApiService: MovieApiService): MovieRemoteDataSource =
        MovieRemoteDataSourceImpl(movieApiService)
}