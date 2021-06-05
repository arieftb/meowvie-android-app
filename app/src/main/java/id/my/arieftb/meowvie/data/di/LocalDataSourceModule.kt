package id.my.arieftb.meowvie.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.my.arieftb.meowvie.data.local.language.LanguageLocalDataSource
import id.my.arieftb.meowvie.data.local.language.LanguageLocalDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {
    @Provides
    @Singleton
    fun provideLanguageLocalDataSource(): LanguageLocalDataSource = LanguageLocalDataSourceImpl()
}