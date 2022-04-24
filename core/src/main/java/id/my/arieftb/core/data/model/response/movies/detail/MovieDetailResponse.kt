package id.my.arieftb.core.data.model.response.movies.detail

import com.google.gson.annotations.SerializedName
import id.my.arieftb.core.BuildConfig
import id.my.arieftb.core.domain.model.base.ContentDetail
import id.my.arieftb.core.domain.model.movie.MovieDetail

data class MovieDetailResponse(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: BelongsToCollection? = null,
    @SerializedName("budget")
    val budget: Int? = null,
    @SerializedName("genres")
    val genres: List<Genre>? = null,
    @SerializedName("homepage")
    val homepage: String? = null,
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("imdb_id")
    val imdbId: String? = null,
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
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>? = null,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("revenue")
    val revenue: Long? = null,
    @SerializedName("runtime")
    val runtime: Int? = null,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("tagline")
    val tagline: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("video")
    val video: Boolean? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Long? = null,
    @SerializedName("success")
    val success: Boolean? = true,
    @SerializedName("status_code")
    val statusCode: Long? = 0L,
    @SerializedName("status_message")
    val statusMessage: String? = null
) : MovieDetailResponseMapper {
    override fun toMovieDetail(): ContentDetail {
        return MovieDetail(
            id = this.id!!,
            title = this.originalTitle!!,
        ).apply {
            posterPath =
                BuildConfig.BASE_URL_IMAGE_PORTRAIT_BIG + this@MovieDetailResponse.posterPath
            bannerPath = if (this@MovieDetailResponse.backdropPath != null) {
                BuildConfig.BASE_URL_IMAGE_LANDSCAPE + this@MovieDetailResponse.backdropPath
            } else BuildConfig.BASE_URL_IMAGE_PORTRAIT + this@MovieDetailResponse.posterPath
            releaseDate = this@MovieDetailResponse.releaseDate ?: ""
            overview = this@MovieDetailResponse.overview ?: ""
            genre = this@MovieDetailResponse.genres?.joinToString(", ") {
                it.name.toString()
            } ?: ""
        }
    }
}

interface MovieDetailResponseMapper {
    fun toMovieDetail(): ContentDetail
}