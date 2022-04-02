package id.my.arieftb.data.model.response.contents.search


import com.google.gson.annotations.SerializedName

data class ContentSearchResponse(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val contentResults: List<ContentResult>? = null,
    @SerializedName("total_pages")
    val totalPages: Long? = null,
    @SerializedName("total_results")
    val totalResults: Long? = null
)