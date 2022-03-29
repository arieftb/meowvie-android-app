package id.my.arieftb.meowvie.data.model.response.movies


import com.google.gson.annotations.SerializedName
import id.my.arieftb.meowvie.BuildConfig
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.model.movie.Movie

data class MovieResult(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("genre_ids")
    val genreIds: List<Int>? = null,
    @SerializedName("id")
    val id: Long? = null,
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
    val voteAverage: String? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null
) : MovieResultMapper {
    override fun toMovie(): Content {
        return Movie(
            id = this.id!!,
            title = this.title!!
        ).apply {
            posterPath =
                BuildConfig.BASE_URL_IMAGE_PORTRAIT_BIG + this@MovieResult.posterPath
            bannerPath = if (this@MovieResult.backdropPath != null) {
                BuildConfig.BASE_URL_IMAGE_LANDSCAPE + this@MovieResult.backdropPath
            } else BuildConfig.BASE_URL_IMAGE_PORTRAIT + this@MovieResult.posterPath
            type = ContentType.MOVIE
        }
    }

}

interface MovieResultMapper {
    fun toMovie(): Content
}