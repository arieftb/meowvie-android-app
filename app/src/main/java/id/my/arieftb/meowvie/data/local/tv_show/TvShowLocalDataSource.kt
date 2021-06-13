package id.my.arieftb.meowvie.data.local.tv_show

import id.my.arieftb.meowvie.data.model.entity.TvShowWatchListEntity
import id.my.arieftb.meowvie.data.model.request.content.ContentSaveRequest

interface TvShowLocalDataSource {
    suspend fun checkWatchList(code: Long): TvShowWatchListEntity?
    suspend fun deleteWatchList(code: Long): Int
}