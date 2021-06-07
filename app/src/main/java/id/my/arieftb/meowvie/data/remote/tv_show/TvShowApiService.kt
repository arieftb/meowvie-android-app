package id.my.arieftb.meowvie.data.remote.tv_show

import id.my.arieftb.meowvie.data.model.response.tv_shows.TvShowsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

@JvmSuppressWildcards
interface TvShowApiService {
    @GET("discover/tv")
    suspend fun getTvShows(@QueryMap queryMap: Map<String, Any>): Response<TvShowsResponse>
}