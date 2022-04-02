package id.my.arieftb.meowvie.data.remote.tv_show

import id.my.arieftb.meowvie.domain.model.request.detail.DetailRequest
import id.my.arieftb.meowvie.domain.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.data.model.response.tv_shows.TvShowsResponse
import id.my.arieftb.meowvie.data.model.response.tv_shows.detail.TvShowDetailResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface TvShowRemoteDataSource {
    fun fetchAll(request: DiscoverRequest): Flow<Response<TvShowsResponse>>
    fun fetch(request: DetailRequest): Flow<Response<TvShowDetailResponse>>
}