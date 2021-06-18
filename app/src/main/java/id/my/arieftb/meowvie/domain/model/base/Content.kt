package id.my.arieftb.meowvie.domain.model.base

import id.my.arieftb.meowvie.BuildConfig
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.data.model.entity.WatchListEntity
import id.my.arieftb.meowvie.data.model.response.contents.search.ContentResult
import id.my.arieftb.meowvie.data.model.response.movies.MovieResult
import id.my.arieftb.meowvie.data.model.response.tv_shows.TvShowResult

open class Content(
    var id: Long? = -1,
    var title: String? = "",
    var bannerPath: String? = null,
    var posterPath: String? = null,
    var type: ContentType? = ContentType.MOVIE
) : ContentMovieMapper, ContentTvShowMapper, ContentSearchMapper, ContentWatchListMapper {
    override fun mapFromMovieResult(response: MovieResult): Content {
        return Content().apply {
            this.id = response.id
            this.title = response.originalTitle
            this.bannerPath = if (response.backdropPath != null) {
                BuildConfig.BASE_URL_IMAGE_LANDSCAPE + response.backdropPath
            } else BuildConfig.BASE_URL_IMAGE_PORTRAIT + response.posterPath
            this.posterPath = BuildConfig.BASE_URL_IMAGE_PORTRAIT + response.posterPath
            this.type = ContentType.MOVIE
        }
    }

    override fun mapFromTvShowResult(response: TvShowResult): Content {
        return Content().apply {
            this.id = response.id
            this.title = response.originalName
            this.bannerPath = if (response.backdropPath != null) {
                BuildConfig.BASE_URL_IMAGE_LANDSCAPE + response.backdropPath
            } else BuildConfig.BASE_URL_IMAGE_PORTRAIT + response.posterPath
            this.posterPath = BuildConfig.BASE_URL_IMAGE_PORTRAIT + response.posterPath
            this.type = ContentType.TV
        }
    }

    override fun mapFromSearchResult(response: ContentResult): Content {
        return Content().apply {
            this.id = response.id
            this.title = response.originalName ?: response.originalTitle
            this.bannerPath = if (response.backdropPath != null) {
                BuildConfig.BASE_URL_IMAGE_LANDSCAPE + response.backdropPath
            } else BuildConfig.BASE_URL_IMAGE_PORTRAIT + response.posterPath
            this.posterPath = BuildConfig.BASE_URL_IMAGE_PORTRAIT + response.posterPath
            this.type = if (response.mediaType.equals("tv")) {
                ContentType.TV
            } else {
                ContentType.MOVIE
            }
        }
    }

    override fun mapFromWatchlistResult(response: WatchListEntity): Content {
        return Content().apply {
            this.id = response.code
            this.title = response.title
            this.bannerPath = if (response.bannerPath != null) {
                BuildConfig.BASE_URL_IMAGE_LANDSCAPE + response.bannerPath
            } else BuildConfig.BASE_URL_IMAGE_PORTRAIT + response.posterPath
            this.posterPath = BuildConfig.BASE_URL_IMAGE_PORTRAIT + response.posterPath
            this.type = if (response.type == "tv") {
                ContentType.TV
            } else {
                ContentType.MOVIE
            }
        }
    }

}

interface ContentMovieMapper {
    fun mapFromMovieResult(response: MovieResult): Content
}

interface ContentTvShowMapper {
    fun mapFromTvShowResult(response: TvShowResult): Content
}

interface ContentSearchMapper {
    fun mapFromSearchResult(response: ContentResult): Content
}

interface ContentWatchListMapper {
    fun mapFromWatchlistResult(response: WatchListEntity): Content
}