package id.my.arieftb.meowvie.data.remote.tv_show

import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.data.model.response.tv_shows.TvShowsResponse
import retrofit2.Response
import javax.inject.Inject

class TvShowRemoteDataSourceImpl @Inject constructor(private val apiService: TvShowApiService) :
    TvShowRemoteDataSource {
    override suspend fun fetchAll(request: DiscoverRequest): Response<TvShowsResponse> {
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

        return apiService.getTvShows(queryMap)
    }
}