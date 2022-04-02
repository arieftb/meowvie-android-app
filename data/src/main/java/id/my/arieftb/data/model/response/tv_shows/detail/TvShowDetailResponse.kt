package id.my.arieftb.data.model.response.tv_shows.detail


import com.google.gson.annotations.SerializedName
import id.my.arieftb.data.BuildConfig
import id.my.arieftb.meowvie.domain.model.entity.base.ContentDetail
import id.my.arieftb.meowvie.domain.model.entity.tv_show.TvShowDetail

data class TvShowDetailResponse(
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("created_by")
    val createdBy: List<CreatedBy>? = null,
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>? = null,
    @SerializedName("first_air_date")
    val firstAirDate: String? = null,
    @SerializedName("genres")
    val genres: List<Genre>? = null,
    @SerializedName("homepage")
    val homepage: String? = null,
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("in_production")
    val inProduction: Boolean? = null,
    @SerializedName("languages")
    val languages: List<String>? = null,
    @SerializedName("last_air_date")
    val lastAirDate: String? = null,
    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAir? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: NextEpisodeToAir? = null,
    @SerializedName("networks")
    val networks: List<Network>? = null,
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int? = null,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int? = null,
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
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>? = null,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>? = null,
    @SerializedName("seasons")
    val seasons: List<Season>? = null,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("tagline")
    val tagline: String? = null,
    @SerializedName("type")
    val type: String? = null,
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
): TvShowDetailResponseMapper {
    override fun toTvShowDetail(): ContentDetail {
        return TvShowDetail(
            id = this.id!!,
            title = this.name!!,
        ).apply {
            posterPath =
                BuildConfig.BASE_URL_IMAGE_PORTRAIT_BIG + this@TvShowDetailResponse.posterPath
            bannerPath = if (this@TvShowDetailResponse.backdropPath != null) {
                BuildConfig.BASE_URL_IMAGE_LANDSCAPE + this@TvShowDetailResponse.backdropPath
            } else BuildConfig.BASE_URL_IMAGE_PORTRAIT + this@TvShowDetailResponse.posterPath
            releaseDate = this@TvShowDetailResponse.firstAirDate ?: ""
            overview = this@TvShowDetailResponse.overview ?: ""
            genre = this@TvShowDetailResponse.genres?.joinToString(", ") {
                it.name.toString()
            } ?: ""
        }
    }

}

interface TvShowDetailResponseMapper {
    fun toTvShowDetail(): ContentDetail
}