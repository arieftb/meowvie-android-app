package id.my.arieftb.meowvie.presentation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.my.arieftb.core.domain.usecase.watch_list.GetWatchListAllUseCase
import id.my.arieftb.core.domain.usecase.watch_list.GetWatchListAllUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FavoriteModule {
    @Binds
    @Singleton
    abstract fun provideGetWatchListAllUseCase(getWatchListAllUseCase: GetWatchListAllUseCaseImpl): GetWatchListAllUseCase
}