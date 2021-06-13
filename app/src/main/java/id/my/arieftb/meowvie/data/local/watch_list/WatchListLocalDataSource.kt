package id.my.arieftb.meowvie.data.local.watch_list

import androidx.paging.DataSource
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.data.model.entity.WatchListEntity
import id.my.arieftb.meowvie.data.model.request.content.ContentSaveRequest

interface WatchListLocalDataSource {
    suspend fun saveWatchList(request: ContentSaveRequest): Long
    suspend fun checkWatchList(code: Long, type: ContentType): WatchListEntity?
    suspend fun deleteWatchList(code: Long, type: ContentType): Int
    fun fetchAllWatchList(): DataSource.Factory<Int, WatchListEntity>
}