package id.my.arieftb.meowvie.domain.model.base

import id.my.arieftb.meowvie.BuildConfig
import id.my.arieftb.meowvie.data.model.response.movies.detail.MovieDetailResponse
import id.my.arieftb.meowvie.data.model.response.tv_shows.detail.TvShowDetailResponse

open class ContentDetail(
    var id: Long? = -1,
    var title: String? = "",
    var posterPath: String? = null,
    var releaseDate: String? = "",
    var overview: String? = null,
    var genre: String? = null,
    var rating: Double? = 0.0
) : ContentMovieDetailMapper, ContentTvShowDetailMapper {
    override fun mapFromMovieDetailResponse(response: MovieDetailResponse?): ContentDetail {
        return ContentDetail().apply {
            this.id = response?.id
            this.title = response?.title
            this.posterPath = BuildConfig.BASE_URL_IMAGE_PORTRAIT_BIG + response?.posterPath
            this.releaseDate = response?.releaseDate
            this.overview = response?.overview
            this.genre = response?.genres?.joinToString(", ") {
                it.name.toString()
            }
            this.rating = response?.voteAverage?.div(2) ?: 0.0
        }
    }

    override fun mapFromTvShowDetailResponse(response: TvShowDetailResponse?): ContentDetail {
        return ContentDetail().apply {
            this.id = response?.id
            this.title = response?.name
            this.posterPath = BuildConfig.BASE_URL_IMAGE_PORTRAIT_BIG + response?.posterPath
            this.releaseDate = response?.firstAirDate
            this.overview = response?.overview
            this.genre = response?.genres?.joinToString(", ") {
                it.name.toString()
            }
            this.rating = response?.voteAverage?.div(2) ?: 0.0
        }
    }

}

interface ContentMovieDetailMapper {
    fun mapFromMovieDetailResponse(response: MovieDetailResponse?): ContentDetail
}

interface ContentTvShowDetailMapper {
    fun mapFromTvShowDetailResponse(response: TvShowDetailResponse?): ContentDetail
}