package id.my.arieftb.meowvie.data.remote.tv_show

import id.my.arieftb.meowvie.data.model.request.detail.DetailRequest
import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.data.model.response.tv_shows.TvShowsResponse
import id.my.arieftb.meowvie.data.model.response.tv_shows.detail.TvShowDetailResponse
import retrofit2.Response

interface TvShowRemoteDataSource {
    suspend fun fetchAll(request: DiscoverRequest): Response<TvShowsResponse>
    suspend fun fetch(request: DetailRequest): Response<TvShowDetailResponse>
}