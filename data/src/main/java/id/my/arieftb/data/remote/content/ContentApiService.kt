package id.my.arieftb.data.remote.content

import id.my.arieftb.data.model.response.contents.search.ContentSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

@JvmSuppressWildcards
interface ContentApiService {
    @GET("search/multi")
    suspend fun search(@QueryMap queryMap: Map<String, Any>): Response<ContentSearchResponse>
}