package id.my.arieftb.meowvie.presentation.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.my.arieftb.core.domain.usecase.watch_list.GetWatchListAllUseCase

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun getWatchListAllUseCase(): GetWatchListAllUseCase
}