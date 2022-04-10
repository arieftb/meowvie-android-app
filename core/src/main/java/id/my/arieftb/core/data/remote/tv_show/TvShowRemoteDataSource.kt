package id.my.arieftb.core.data.remote.tv_show

import id.my.arieftb.core.data.model.request.detail.DetailRequest
import id.my.arieftb.core.data.model.request.discover.DiscoverRequest
import id.my.arieftb.core.data.model.response.tv_shows.TvShowsResponse
import id.my.arieftb.core.data.model.response.tv_shows.detail.TvShowDetailResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface TvShowRemoteDataSource {
    fun fetchAll(request: DiscoverRequest): Flow<Response<TvShowsResponse>>
    fun fetch(request: DetailRequest): Flow<Response<TvShowDetailResponse>>
}