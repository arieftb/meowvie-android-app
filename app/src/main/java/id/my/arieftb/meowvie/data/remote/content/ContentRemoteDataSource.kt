package id.my.arieftb.meowvie.data.remote.content

import id.my.arieftb.meowvie.data.model.request.content.ContentSearchRequest
import id.my.arieftb.meowvie.data.model.response.contents.search.ContentSearchResponse
import retrofit2.Response

interface ContentRemoteDataSource {
    suspend fun search(request: ContentSearchRequest): Response<ContentSearchResponse>
}