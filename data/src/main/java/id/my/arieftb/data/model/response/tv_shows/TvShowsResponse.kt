package id.my.arieftb.data.model.response.tv_shows


import com.google.gson.annotations.SerializedName

data class TvShowsResponse(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val tvShowResults: List<TvShowResult>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
)