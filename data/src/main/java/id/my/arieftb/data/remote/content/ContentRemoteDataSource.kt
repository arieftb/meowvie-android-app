package id.my.arieftb.data.remote.content

import id.my.arieftb.meowvie.domain.model.request.content.ContentSearchRequest
import id.my.arieftb.data.model.response.contents.search.ContentSearchResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ContentRemoteDataSource {
    fun search(request: ContentSearchRequest): Flow<Response<ContentSearchResponse>>
}