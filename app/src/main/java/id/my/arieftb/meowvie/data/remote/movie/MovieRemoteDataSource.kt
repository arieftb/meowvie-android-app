package id.my.arieftb.meowvie.data.remote.movie

import id.my.arieftb.meowvie.data.model.request.detail.DetailRequest
import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.data.model.response.movies.MoviesResponse
import id.my.arieftb.meowvie.data.model.response.movies.detail.MovieDetailResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieRemoteDataSource {
    fun fetchAll(request: DiscoverRequest): Flow<Response<MoviesResponse>>
    fun fetch(request: DetailRequest): Flow<Response<MovieDetailResponse>>
}