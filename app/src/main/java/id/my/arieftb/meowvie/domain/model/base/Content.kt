package id.my.arieftb.meowvie.domain.model.base

import id.my.arieftb.meowvie.BuildConfig
import id.my.arieftb.meowvie.data.model.response.movies.MovieResult
import id.my.arieftb.meowvie.data.model.response.tv_shows.TvShowResult

open class Content(
    var id: Long? = -1,
    var title: String? = "",
    var bannerPath: String? = null,
    var posterPath: String? = null,
) : ContentMovieMapper, ContentTvShowMapper {
    override fun mapFromMovieResult(response: MovieResult): Content {
        return Content().apply {
            this.id = response.id
            this.title = response.title
            this.bannerPath = if (response.backdropPath != null) {
                BuildConfig.BASE_URL_IMAGE_LANDSCAPE + response.backdropPath
            } else BuildConfig.BASE_URL_IMAGE_PORTRAIT + response.posterPath
            this.posterPath = BuildConfig.BASE_URL_IMAGE_PORTRAIT + response.posterPath
        }
    }

    override fun mapFromTvShowResult(response: TvShowResult): Content {
        return Content().apply {
            this.id = response.id
            this.title = response.name
            this.bannerPath = if (response.backdropPath != null) {
                BuildConfig.BASE_URL_IMAGE_LANDSCAPE + response.backdropPath
            } else BuildConfig.BASE_URL_IMAGE_PORTRAIT + response.posterPath
            this.posterPath = BuildConfig.BASE_URL_IMAGE_PORTRAIT + response.posterPath
        }
    }

}

interface ContentMovieMapper {
    fun mapFromMovieResult(response: MovieResult): Content
}

interface ContentTvShowMapper {
    fun mapFromTvShowResult(response: TvShowResult): Content
}