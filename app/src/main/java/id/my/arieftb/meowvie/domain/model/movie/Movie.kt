package id.my.arieftb.meowvie.domain.model.movie

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
            bannerPath = response?.backdropPath
            posterPath = response?.posterPath
            releaseDate = response?.releaseDate
        }
    }
}

interface MovieMapper : BaseMapper.ResponseDataMapper<MovieResult, Movie>