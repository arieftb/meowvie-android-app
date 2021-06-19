package id.my.arieftb.meowvie.domain.repo

import androidx.paging.PagingData
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.data.model.request.content.ContentSaveRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import kotlinx.coroutines.flow.Flow

interface WatchListRepository {
    suspend fun saveWatchList(request: ContentSaveRequest): Result<Boolean>
    suspend fun checkWatchList(code: Long, type: ContentType): Result<Boolean>
    suspend fun removeWatchList(code: Long, type: ContentType): Result<Boolean>
    fun fetchAllWatchList(limit: Int, data: Content): Flow<PagingData<Content>>
}