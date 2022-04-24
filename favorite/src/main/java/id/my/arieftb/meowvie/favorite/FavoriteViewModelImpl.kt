package id.my.arieftb.meowvie.favorite

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import id.my.arieftb.core.domain.model.base.Content
import id.my.arieftb.core.domain.usecase.watch_list.GetWatchListAllUseCase
import kotlinx.coroutines.flow.Flow

class FavoriteViewModelImpl constructor(
    private val getWatchListAllUseCase: GetWatchListAllUseCase
) : ViewModel(), FavoriteViewModel {
    override fun getWatchListAll(): Flow<PagingData<Content>> {
        return getWatchListAllUseCase.invoke()
    }
}