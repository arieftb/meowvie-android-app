package id.my.arieftb.meowvie.persentation.feature.favorite

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.my.arieftb.meowvie.data.model.entity.WatchListEntity

interface FavoriteViewModel {
    fun getWatchListAll(): LiveData<PagedList<WatchListEntity>>
}