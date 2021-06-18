package id.my.arieftb.meowvie.presentation.feature.favorite

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.PagingData
import id.my.arieftb.meowvie.data.model.entity.WatchListEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteViewModel {
    fun getWatchListAll(): Flow<PagingData<WatchListEntity>>
}