package id.my.arieftb.meowvie.data.model.response.contents.search


import com.google.gson.annotations.SerializedName
import id.my.arieftb.meowvie.BuildConfig
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.domain.model.base.Content

data class ContentResult(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("genre_ids")
    val genreIds: List<Long>? = null,
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("media_type")
    val mediaType: String? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("video")
    val video: Boolean? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Long? = null,
    @SerializedName("first_air_date")
    val firstAirDate: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("origin_country")
    val originCountry: List<String>? = null,
    @SerializedName("original_name")
    val originalName: String? = null
) : ContentResultMapper {
    override fun toContent(): Content {
        return Content(
            id = this.id!!,
            title = this.name ?: this.originalTitle ?: ""
        ).apply {
            posterPath =
                BuildConfig.BASE_URL_IMAGE_PORTRAIT_BIG + this@ContentResult.posterPath
            bannerPath = if (this@ContentResult.backdropPath != null) {
                BuildConfig.BASE_URL_IMAGE_LANDSCAPE + this@ContentResult.backdropPath
            } else BuildConfig.BASE_URL_IMAGE_PORTRAIT + this@ContentResult.posterPath
            type = if (this@ContentResult.mediaType.equals("tv")) {
                ContentType.TV
            } else {
                ContentType.MOVIE
            }
        }
    }

}

interface ContentResultMapper {
    fun toContent(): Content
}