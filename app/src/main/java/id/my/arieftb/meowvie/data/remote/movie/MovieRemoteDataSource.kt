package id.my.arieftb.meowvie.data.remote.movie

import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.data.model.response.movies.MoviesResponse
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun fetchAll(request: DiscoverRequest): Response<MoviesResponse>
}