package id.my.arieftb.meowvie.domain.repo

import androidx.paging.PagingData
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.data.model.request.content.ContentSaveRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import kotlinx.coroutines.flow.Flow

interface WatchListRepository {
    fun saveWatchList(request: ContentSaveRequest): Flow<Result<Boolean>>
    fun checkWatchList(code: Long, type: ContentType): Flow<Result<Boolean>>
    fun removeWatchList(code: Long, type: ContentType): Flow<Result<Boolean>>
    fun fetchAllWatchList(limit: Int): Flow<PagingData<Content>>
}