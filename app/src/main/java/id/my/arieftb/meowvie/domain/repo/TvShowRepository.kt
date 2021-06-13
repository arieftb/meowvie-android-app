package id.my.arieftb.meowvie.domain.repo

import id.my.arieftb.meowvie.data.model.request.content.ContentSaveRequest
import id.my.arieftb.meowvie.data.model.request.detail.DetailRequest
import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.model.base.ContentDetail
import id.my.arieftb.meowvie.domain.model.tv_show.TvShow
import id.my.arieftb.meowvie.domain.model.tv_show.TvShowDetail

interface TvShowRepository {
    suspend fun fetchAll(request: DiscoverRequest, data: TvShow): Result<List<Content>>
    suspend fun fetch(request: DetailRequest, data: TvShowDetail): Result<ContentDetail>
    suspend fun save(request: ContentSaveRequest): Result<Boolean>
}