package id.my.arieftb.meowvie.data.local.movie

import id.my.arieftb.meowvie.data.model.entity.MovieEntity
import id.my.arieftb.meowvie.data.model.request.content.ContentSaveRequest

interface MovieLocalDataSource {
    suspend fun saveMovie(request: ContentSaveRequest): Long
    suspend fun checkMovie(code: Long): MovieEntity?
}