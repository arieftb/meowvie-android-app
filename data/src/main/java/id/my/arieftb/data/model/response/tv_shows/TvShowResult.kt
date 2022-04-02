package id.my.arieftb.data.model.response.tv_shows


import com.google.gson.annotations.SerializedName
import id.my.arieftb.data.BuildConfig
import id.my.arieftb.meowvie.domain.constant.ContentType
import id.my.arieftb.meowvie.domain.model.entity.base.Content
import id.my.arieftb.meowvie.domain.model.entity.tv_show.TvShow

data class TvShowResult(
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("first_air_date")
    val firstAirDate: String? = null,
    @SerializedName("genre_ids")
    val genreIds: List<Int>? = null,
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("origin_country")
    val originCountry: List<String>? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_name")
    val originalName: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("vote_average")
    val voteAverage: String? = null,
    @SerializedName("vote_count")
    val voteCount: Long? = null
) : TvShowResultMapper {
    override fun toTvShow(): Content {
        return TvShow(
            id = this.id!!,
            title = this.name!!
        ).apply {
            posterPath =
                BuildConfig.BASE_URL_IMAGE_PORTRAIT_BIG + this@TvShowResult.posterPath
            bannerPath = if (this@TvShowResult.backdropPath != null) {
                BuildConfig.BASE_URL_IMAGE_LANDSCAPE + this@TvShowResult.backdropPath
            } else BuildConfig.BASE_URL_IMAGE_PORTRAIT + this@TvShowResult.posterPath
            type = ContentType.TV
        }
    }

}

interface TvShowResultMapper {
    fun toTvShow(): Content
}