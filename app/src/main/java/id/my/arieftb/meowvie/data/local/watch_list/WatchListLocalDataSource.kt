package id.my.arieftb.meowvie.data.local.watch_list

import androidx.paging.PagingSource
import id.my.arieftb.meowvie.domain.constant.ContentType
import id.my.arieftb.meowvie.data.model.entity.WatchListEntity
import id.my.arieftb.meowvie.data.model.request.content.ContentSaveRequest
import kotlinx.coroutines.flow.Flow

interface WatchListLocalDataSource {
    fun saveWatchList(request: ContentSaveRequest): Flow<Long>
    fun checkWatchList(code: Long, type: ContentType): Flow<WatchListEntity?>
    fun deleteWatchList(code: Long, type: ContentType): Flow<Int>
    fun fetchAllWatchList(): PagingSource<Int, WatchListEntity>
}