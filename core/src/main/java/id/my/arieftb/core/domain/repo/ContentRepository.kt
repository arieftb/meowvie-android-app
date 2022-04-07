package id.my.arieftb.core.domain.repo

import id.my.arieftb.core.data.model.request.content.ContentSearchRequest
import id.my.arieftb.core.domain.model.Result
import id.my.arieftb.core.domain.model.base.Content
import kotlinx.coroutines.flow.Flow

interface ContentRepository {
    fun search(request: ContentSearchRequest): Flow<Result<List<Content>>>
}