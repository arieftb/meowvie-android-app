package id.my.arieftb.meowvie.domain.model.movie

import id.my.arieftb.meowvie.BuildConfig
import id.my.arieftb.meowvie.data.model.response.movies.detail.MovieDetailResponse
import id.my.arieftb.meowvie.domain.base.BaseMapper

class MovieDetail(
    var id: Long? = -1,
    var title: String? = "",
    var posterPath: String? = null,
    var releaseDate: String? = "",
    var overview: String? = null,
    var genre: String? = null,
    var rating: Double? = 0.0
) : MovieDetailMapper {
    override fun mapFromResponse(response: MovieDetailResponse?): MovieDetail {
        return MovieDetail().apply {
            id = response?.id
            title = response?.title
            posterPath = BuildConfig.BASE_URL_IMAGE_PORTRAIT_BIG + response?.posterPath
            releaseDate = response?.releaseDate
            overview = response?.overview
            genre = response?.genres?.joinToString(separator = ", ") {
                it.name.toString()
            }
            rating = response?.voteAverage
        }
    }
}

interface MovieDetailMapper : BaseMapper.ResponseDataMapper<MovieDetailResponse, MovieDetail>