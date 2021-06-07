package id.my.arieftb.meowvie.data.remote.tv_show

import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.data.model.response.tv_shows.TvShowsResponse
import retrofit2.Response

interface TvShowRemoteDataSource {
    suspend fun fetchAll(request: DiscoverRequest): Response<TvShowsResponse>
}