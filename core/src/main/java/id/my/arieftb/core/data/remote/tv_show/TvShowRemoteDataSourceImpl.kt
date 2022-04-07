package id.my.arieftb.core.data.remote.tv_show

import id.my.arieftb.core.data.model.request.detail.DetailRequest
import id.my.arieftb.core.data.model.request.discover.DiscoverRequest
import id.my.arieftb.core.data.model.response.tv_shows.TvShowsResponse
import id.my.arieftb.core.data.model.response.tv_shows.detail.TvShowDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class TvShowRemoteDataSourceImpl @Inject constructor(private val apiService: TvShowApiService) :
    TvShowRemoteDataSource {
    override fun fetchAll(request: DiscoverRequest): Flow<Response<TvShowsResponse>> {
        val queryMap = HashMap<String, Any>()
        queryMap["api_key"] = request.apiKey
        queryMap["region"] = request.region
        queryMap["language"] = request.language
        queryMap["page"] = request.page

        request.sortBy?.let {
            queryMap["sort_by"] = it
        }

        request.releaseDateGte?.let {
            queryMap["first_air_date.gte"] = it
        }

        request.releaseDateLte?.let {
            queryMap["first_air_date.lte"] = it
        }

        return flow {
            emit(apiService.getTvShows(queryMap))
        }
    }

    override suspend fun fetch(request: DetailRequest): Response<TvShowDetailResponse> {
        val queryMap = HashMap<String, Any>().apply {
            this["api_key"] = request.apiKey
            this["language"] = request.language
        }

        return apiService.getTvShow(request.id.toString(), queryMap)
    }
}