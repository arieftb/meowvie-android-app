package id.my.arieftb.meowvie.domain.usecase.watch_list

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.PagingData
import id.my.arieftb.meowvie.data.model.entity.WatchListEntity
import kotlinx.coroutines.flow.Flow

interface GetWatchListAllUseCase {
    fun invoke(limit: Int = 4): Flow<PagingData<WatchListEntity>>
}