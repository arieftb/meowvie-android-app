package id.my.arieftb.meowvie.favorite.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.my.arieftb.core.domain.usecase.watch_list.GetWatchListAllUseCase
import id.my.arieftb.meowvie.favorite.FavoriteViewModel
import id.my.arieftb.meowvie.favorite.FavoriteViewModelImpl
import javax.inject.Inject

class FavoriteViewModelFactory @Inject constructor(
    private val getWatchListAllUseCase: GetWatchListAllUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModelImpl(getWatchListAllUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

}