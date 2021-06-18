package id.my.arieftb.meowvie.presentation.feature.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.my.arieftb.meowvie.data.model.entity.WatchListEntity
import id.my.arieftb.meowvie.domain.usecase.watch_list.GetWatchListAllUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModelImpl @Inject constructor(
    private val getWatchListAllUseCase: GetWatchListAllUseCase
) : ViewModel(), FavoriteViewModel {
    override fun getWatchListAll(): Flow<PagingData<WatchListEntity>> {
        return getWatchListAllUseCase.invoke()
    }
}