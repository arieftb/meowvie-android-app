package id.my.arieftb.meowvie.domain.repo

import id.my.arieftb.meowvie.data.model.request.content.ContentSearchRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content

interface ContentRepository {
    suspend fun search(request: ContentSearchRequest, data: Content): Result<List<Content>>
}