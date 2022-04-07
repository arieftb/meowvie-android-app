package id.my.arieftb.core.domain.repo

import id.my.arieftb.core.data.model.request.detail.DetailRequest
import id.my.arieftb.core.data.model.request.discover.DiscoverRequest
import id.my.arieftb.core.domain.model.Result
import id.my.arieftb.core.domain.model.base.Content
import id.my.arieftb.core.domain.model.base.ContentDetail
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {
    fun fetchAll(request: DiscoverRequest): Flow<Result<List<Content>>>
    suspend fun fetch(request: DetailRequest): Result<ContentDetail>
}