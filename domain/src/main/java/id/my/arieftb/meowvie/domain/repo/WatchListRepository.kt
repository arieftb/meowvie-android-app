package id.my.arieftb.meowvie.domain.repo

import androidx.paging.PagingData
import id.my.arieftb.meowvie.domain.constant.ContentType
import id.my.arieftb.meowvie.domain.model.entity.Result
import id.my.arieftb.meowvie.domain.model.entity.base.Content
import id.my.arieftb.meowvie.domain.model.request.content.ContentSaveRequest
import kotlinx.coroutines.flow.Flow

interface WatchListRepository {
    fun saveWatchList(request: ContentSaveRequest): Flow<Result<Boolean>>
    fun checkWatchList(code: Long, type: ContentType): Flow<Result<Boolean>>
    fun removeWatchList(code: Long, type: ContentType): Flow<Result<Boolean>>
    fun fetchAllWatchList(limit: Int): Flow<PagingData<Content>>
}