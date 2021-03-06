package id.my.arieftb.core.data.remote.movie

import id.my.arieftb.core.data.model.response.movies.MoviesResponse
import id.my.arieftb.core.data.model.response.movies.detail.MovieDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

@JvmSuppressWildcards
interface MovieApiService {
    @GET("discover/movie")
    suspend fun getMovies(@QueryMap queryMap: Map<String, Any>): Response<MoviesResponse>

    @GET("movie/{id}")
    suspend fun getMovie(@Path("id") id: String, @QueryMap queryMap: Map<String, Any>): Response<MovieDetailResponse>
}