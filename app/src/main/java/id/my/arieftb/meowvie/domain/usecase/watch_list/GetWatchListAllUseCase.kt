package id.my.arieftb.meowvie.domain.usecase.watch_list

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.my.arieftb.meowvie.data.model.entity.WatchListEntity

interface GetWatchListAllUseCase {
    fun invoke(limit: Int = 4): LiveData<PagedList<WatchListEntity>>
}