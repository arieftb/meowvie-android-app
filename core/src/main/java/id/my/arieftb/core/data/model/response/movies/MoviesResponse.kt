package id.my.arieftb.core.data.model.response.movies


import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val movieResults: List<MovieResult>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
)