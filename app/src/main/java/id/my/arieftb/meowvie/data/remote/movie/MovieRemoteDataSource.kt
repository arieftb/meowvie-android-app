package id.my.arieftb.meowvie.data.remote.movie

import id.my.arieftb.meowvie.data.model.request.detail.DetailRequest
import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.data.model.response.movies.MoviesResponse
import id.my.arieftb.meowvie.data.model.response.movies.detail.MovieDetailResponse
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun fetchAll(request: DiscoverRequest): Response<MoviesResponse>
    suspend fun fetch(request: DetailRequest): Response<MovieDetailResponse>
}