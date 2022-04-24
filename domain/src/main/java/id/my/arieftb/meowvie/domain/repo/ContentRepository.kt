package id.my.arieftb.meowvie.domain.repo

import id.my.arieftb.meowvie.domain.model.entity.Result
import id.my.arieftb.meowvie.domain.model.entity.base.Content
import id.my.arieftb.meowvie.domain.model.request.content.ContentSearchRequest
import kotlinx.coroutines.flow.Flow

interface ContentRepository {
    fun search(request: ContentSearchRequest): Flow<Result<List<Content>>>
}