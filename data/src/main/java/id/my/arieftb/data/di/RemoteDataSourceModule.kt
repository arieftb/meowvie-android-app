package id.my.arieftb.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.my.arieftb.data.remote.content.ContentApiService
import id.my.arieftb.data.remote.content.ContentRemoteDataSource
import id.my.arieftb.data.remote.content.ContentRemoteDataSourceImpl
import id.my.arieftb.data.remote.movie.MovieApiService
import id.my.arieftb.data.remote.movie.MovieRemoteDataSource
import id.my.arieftb.data.remote.movie.MovieRemoteDataSourceImpl
import id.my.arieftb.data.remote.tv_show.TvShowApiService
import id.my.arieftb.data.remote.tv_show.TvShowRemoteDataSource
import id.my.arieftb.data.remote.tv_show.TvShowRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(movieApiService: MovieApiService): MovieRemoteDataSource =
        MovieRemoteDataSourceImpl(movieApiService)

    @Provides
    @Singleton
    fun provideTvShowRemoteDataSource(apiService: TvShowApiService): TvShowRemoteDataSource =
        TvShowRemoteDataSourceImpl(apiService)

    @Provides
    @Singleton
    fun provideContentRemoteDataSource(apiService: ContentApiService): ContentRemoteDataSource =
        ContentRemoteDataSourceImpl(apiService)
}