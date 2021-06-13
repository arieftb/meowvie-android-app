package id.my.arieftb.meowvie.data.local.movie

import id.my.arieftb.meowvie.data.model.entity.MovieWatchListEntity
import id.my.arieftb.meowvie.data.model.request.content.ContentSaveRequest

interface MovieLocalDataSource {
    suspend fun checkWatchList(code: Long): MovieWatchListEntity?
    suspend fun deleteWatchList(code: Long): Int
}