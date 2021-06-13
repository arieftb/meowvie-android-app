package id.my.arieftb.meowvie.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.my.arieftb.meowvie.data.local.date.DateLocalDataSource
import id.my.arieftb.meowvie.data.local.language.LanguageLocalDataSource
import id.my.arieftb.meowvie.data.local.movie.MovieLocalDataSource
import id.my.arieftb.meowvie.data.local.tv_show.TvShowLocalDataSource
import id.my.arieftb.meowvie.data.local.watch_list.WatchListLocalDataSource
import id.my.arieftb.meowvie.data.remote.movie.MovieRemoteDataSource
import id.my.arieftb.meowvie.data.remote.tv_show.TvShowRemoteDataSource
import id.my.arieftb.meowvie.data.repo.*
import id.my.arieftb.meowvie.domain.repo.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMovieRepository(
        remote: MovieRemoteDataSource,
        local: MovieLocalDataSource
    ): MovieRepository =
        MovieRepositoryImpl(remote, local)

    @Provides
    @Singleton
    fun provideLanguageRepository(local: LanguageLocalDataSource): LanguageRepository =
        LanguageRepositoryImpl(local)

    @Provides
    @Singleton
    fun provideDateRepository(local: DateLocalDataSource): DateRepository =
        DateRepositoryImpl(local)

    @Provides
    @Singleton
    fun provideTvShowRepository(
        remote: TvShowRemoteDataSource,
        local: TvShowLocalDataSource
    ): TvShowRepository =
        TvShowRepositoryImpl(remote, local)

    @Provides
    @Singleton
    fun provideWatchListRepository(
        local: WatchListLocalDataSource
    ): WatchListRepository = WatchListRepositoryImpl(local)
}