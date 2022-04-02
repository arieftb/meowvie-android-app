package id.my.arieftb.meowvie.data.remote.content

import id.my.arieftb.meowvie.domain.model.request.content.ContentSearchRequest
import id.my.arieftb.meowvie.data.model.response.contents.search.ContentSearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class ContentRemoteDataSourceImpl @Inject constructor(
    private val api: ContentApiService
) : ContentRemoteDataSource {
    override fun search(request: ContentSearchRequest): Flow<Response<ContentSearchResponse>> {
        val queryMap = HashMap<String, Any>()
        queryMap["api_key"] = request.apiKey
        queryMap["language"] = request.language
        queryMap["page"] = request.page
        queryMap["query"] = request.keyword

        return flow {
            emit(api.search(queryMap))
        }
    }
}