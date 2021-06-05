package id.my.arieftb.meowvie.data.remote.movie

import id.my.arieftb.meowvie.data.model.response.movies.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MovieApiService {
    @GET("movie")
    suspend fun getMovies(@QueryMap queryMap: Map<String, Any>): Response<MoviesResponse>
}