package id.my.arieftb.core.domain.repo

import androidx.paging.PagingData
import id.my.arieftb.core.domain.constant.ContentType
import id.my.arieftb.core.data.model.request.content.ContentSaveRequest
import id.my.arieftb.core.domain.model.Result
import id.my.arieftb.core.domain.model.base.Content
import kotlinx.coroutines.flow.Flow

interface WatchListRepository {
    fun saveWatchList(request: ContentSaveRequest): Flow<Result<Boolean>>
    fun checkWatchList(code: Long, type: ContentType): Flow<Result<Boolean>>
    fun removeWatchList(code: Long, type: ContentType): Flow<Result<Boolean>>
    fun fetchAllWatchList(limit: Int): Flow<PagingData<Content>>
}