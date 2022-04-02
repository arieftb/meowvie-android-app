package id.my.arieftb.meowvie.domain.repo

import id.my.arieftb.meowvie.data.model.request.detail.DetailRequest
import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.model.base.ContentDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchAll(request: DiscoverRequest): Flow<Result<List<Content>>>
    fun fetch(request: DetailRequest): Flow<Result<ContentDetail>>
}