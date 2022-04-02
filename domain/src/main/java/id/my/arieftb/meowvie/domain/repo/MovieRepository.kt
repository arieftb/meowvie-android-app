package id.my.arieftb.meowvie.domain.repo

import id.my.arieftb.meowvie.domain.model.entity.Result
import id.my.arieftb.meowvie.domain.model.entity.base.Content
import id.my.arieftb.meowvie.domain.model.entity.base.ContentDetail
import id.my.arieftb.meowvie.domain.model.request.detail.DetailRequest
import id.my.arieftb.meowvie.domain.model.request.discover.DiscoverRequest
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchAll(request: DiscoverRequest): Flow<Result<List<Content>>>
    fun fetch(request: DetailRequest): Flow<Result<ContentDetail>>
}