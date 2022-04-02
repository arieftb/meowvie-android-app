package id.my.arieftb.meowvie.domain.repo

import id.my.arieftb.meowvie.data.model.request.content.ContentSearchRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import kotlinx.coroutines.flow.Flow

interface ContentRepository {
    fun search(request: ContentSearchRequest): Flow<Result<List<Content>>>
}