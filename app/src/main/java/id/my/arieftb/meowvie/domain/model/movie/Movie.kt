package id.my.arieftb.meowvie.domain.model.movie

import id.my.arieftb.meowvie.BuildConfig
import id.my.arieftb.meowvie.data.model.response.movies.MovieResult
import id.my.arieftb.meowvie.domain.base.BaseMapper

open class Movie(
    var id: Long? = -1,
    var title: String? = "",
    var bannerPath: String? = null,
    var posterPath: String? = null,
    var releaseDate: String? = ""
) : MovieMapper {
    override fun mapFromResponse(response: MovieResult?): Movie {
        return Movie().apply {
            id = response?.id?.toLong()
            title = response?.originalTitle
            bannerPath = if (response?.backdropPath != null) {
                BuildConfig.BASE_URL_IMAGE_LANDSCAPE + response.backdropPath
            } else BuildConfig.BASE_URL_IMAGE_PORTRAIT + response?.posterPath
            posterPath = BuildConfig.BASE_URL_IMAGE_PORTRAIT + response?.posterPath
            releaseDate = response?.releaseDate
        }
    }
}

interface MovieMapper : BaseMapper.ResponseDataMapper<MovieResult, Movie>