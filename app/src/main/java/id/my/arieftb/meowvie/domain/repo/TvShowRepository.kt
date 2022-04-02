package id.my.arieftb.meowvie.domain.repo

import id.my.arieftb.meowvie.data.model.request.detail.DetailRequest
import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.model.base.ContentDetail
import id.my.arieftb.meowvie.domain.model.tv_show.TvShow
import id.my.arieftb.meowvie.domain.model.tv_show.TvShowDetail
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {
    fun fetchAll(request: DiscoverRequest): Flow<Result<List<Content>>>
    suspend fun fetch(request: DetailRequest): Result<ContentDetail>
}