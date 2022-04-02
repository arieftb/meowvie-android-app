package id.my.arieftb.data.remote.tv_show

import id.my.arieftb.data.model.response.tv_shows.TvShowsResponse
import id.my.arieftb.data.model.response.tv_shows.detail.TvShowDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

@JvmSuppressWildcards
interface TvShowApiService {
    @GET("discover/tv")
    suspend fun getTvShows(@QueryMap queryMap: Map<String, Any>): Response<TvShowsResponse>

    @GET("tv/{id}")
    suspend fun getTvShow(
        @Path("id") id: String,
        @QueryMap queryMap: Map<String, Any>
    ): Response<TvShowDetailResponse>
}