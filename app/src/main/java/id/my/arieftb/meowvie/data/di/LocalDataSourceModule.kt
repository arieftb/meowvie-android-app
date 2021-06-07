package id.my.arieftb.meowvie.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.my.arieftb.meowvie.data.local.date.DateLocalDataSource
import id.my.arieftb.meowvie.data.local.date.DateLocalDataSourceImpl
import id.my.arieftb.meowvie.data.local.language.LanguageLocalDataSource
import id.my.arieftb.meowvie.data.local.language.LanguageLocalDataSourceImpl
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
}