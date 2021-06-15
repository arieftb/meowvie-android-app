package id.my.arieftb.meowvie.data.remote.content

import id.my.arieftb.meowvie.data.model.request.content.ContentSearchRequest
import id.my.arieftb.meowvie.data.model.response.contents.search.ContentSearchResponse
import retrofit2.Response
import javax.inject.Inject

class ContentRemoteDataSourceImpl @Inject constructor(
    private val api: ContentApiService
) : ContentRemoteDataSource {
    override suspend fun search(request: ContentSearchRequest): Response<ContentSearchResponse> {
        val queryMap = HashMap<String, Any>()
        queryMap["api_key"] = request.apiKey
        queryMap["language"] = request.language
        queryMap["page"] = request.page
        queryMap["query"] = request.keyword

        return api.search(queryMap)
    }
}