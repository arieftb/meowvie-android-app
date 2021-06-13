package id.my.arieftb.meowvie.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.my.arieftb.meowvie.data.local.date.DateLocalDataSource
import id.my.arieftb.meowvie.data.local.date.DateLocalDataSourceImpl
import id.my.arieftb.meowvie.data.local.language.LanguageLocalDataSource
import id.my.arieftb.meowvie.data.local.language.LanguageLocalDataSourceImpl
import id.my.arieftb.meowvie.data.local.movie.MovieDao
import id.my.arieftb.meowvie.data.local.movie.MovieLocalDataSource
import id.my.arieftb.meowvie.data.local.movie.MovieLocalDataSourceImpl
import id.my.arieftb.meowvie.data.local.tv_show.TvShowDao
import id.my.arieftb.meowvie.data.local.tv_show.TvShowLocalDataSource
import id.my.arieftb.meowvie.data.local.tv_show.TvShowLocalDataSourceImpl
import id.my.arieftb.meowvie.utils.helper.date.DateHelper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {
    @Provides
    @Singleton
    fun provideLanguageLocalDataSource(): LanguageLocalDataSource = LanguageLocalDataSourceImpl()

    @Provides
    @Singleton
    fun provideDateLocalDataSource(dateHelper: DateHelper): DateLocalDataSource =
        DateLocalDataSourceImpl(dateHelper)

    @Provides
    @Singleton
    fun provideMovieLocalDataSource(movieDao: MovieDao): MovieLocalDataSource =
        MovieLocalDataSourceImpl(movieDao)

    @Provides
    @Singleton
    fun provideTvShowLocalDataSource(tvShowDao: TvShowDao): TvShowLocalDataSource =
        TvShowLocalDataSourceImpl(tvShowDao)
}