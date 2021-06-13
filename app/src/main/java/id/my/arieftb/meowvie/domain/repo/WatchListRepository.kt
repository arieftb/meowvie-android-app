package id.my.arieftb.meowvie.domain.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.data.model.entity.WatchListEntity
import id.my.arieftb.meowvie.data.model.request.content.ContentSaveRequest
import id.my.arieftb.meowvie.domain.model.Result

interface WatchListRepository {
    suspend fun saveWatchList(request: ContentSaveRequest): Result<Boolean>
    suspend fun checkWatchList(code: Long, type: ContentType): Result<Boolean>
    suspend fun removeWatchList(code: Long, type: ContentType): Result<Boolean>
    fun fetchAllWatchList(limit: Int): LiveData<PagedList<WatchListEntity>>
}