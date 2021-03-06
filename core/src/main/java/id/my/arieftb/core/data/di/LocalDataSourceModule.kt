package id.my.arieftb.core.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.my.arieftb.core.data.local.date.DateLocalDataSource
import id.my.arieftb.core.data.local.date.DateLocalDataSourceImpl
import id.my.arieftb.core.data.local.language.LanguageLocalDataSource
import id.my.arieftb.core.data.local.language.LanguageLocalDataSourceImpl
import id.my.arieftb.core.data.local.watch_list.WatchListDao
import id.my.arieftb.core.data.local.watch_list.WatchListLocalDataSource
import id.my.arieftb.core.data.local.watch_list.WatchListLocalDataSourceImpl
import id.my.arieftb.core.domain.utils.date.DateHelper
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
    fun provideWatchListLocalDataSource(dao: WatchListDao): WatchListLocalDataSource =
        WatchListLocalDataSourceImpl(dao)
}