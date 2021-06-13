package id.my.arieftb.meowvie.data.local.tv_show

import id.my.arieftb.meowvie.data.model.request.content.ContentSaveRequest

interface TvShowLocalDataSource {
    suspend fun saveTvShow(request: ContentSaveRequest): Long
}