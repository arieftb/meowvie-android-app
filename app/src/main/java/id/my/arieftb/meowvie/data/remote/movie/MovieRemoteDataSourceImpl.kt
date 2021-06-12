package id.my.arieftb.meowvie.data.remote.movie

import id.my.arieftb.meowvie.data.model.request.detail.DetailRequest
import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.data.model.response.movies.MoviesResponse
import id.my.arieftb.meowvie.data.model.response.movies.detail.MovieDetailResponse
import retrofit2.Response
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(private val movieApiService: MovieApiService) :
    MovieRemoteDataSource {

    override suspend fun fetchAll(request: DiscoverRequest): Response<MoviesResponse> {
        val queryMap = HashMap<String, Any>()
        queryMap["api_key"] = request.apiKey
        queryMap["region"] = request.region
        queryMap["language"] = request.language
        queryMap["page"] = request.page

        request.sortBy?.let {
            queryMap["sort_by"] = it
        }

        request.releaseDateGte?.let {
            queryMap["release_date.gte"] = it
        }

        request.releaseDateLte?.let {
            queryMap["release_date.lte"] = it
        }

        return movieApiService.getMovies(queryMap)
    }

    override suspend fun fetch(request: DetailRequest): Response<MovieDetailResponse> {
        val queryMap = HashMap<String, Any>().apply {
            this["api_key"] = request.apiKey
            this["language"] = request.language
        }

        return movieApiService.getMovie(request.id.toString(), queryMap)
    }

}