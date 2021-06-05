package id.my.arieftb.meowvie.data.remote.movie

import id.my.arieftb.meowvie.data.model.request.movie.MovieRequest
import id.my.arieftb.meowvie.data.model.response.movies.MoviesResponse
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun fetchAll(request: MovieRequest): Response<MoviesResponse>
}