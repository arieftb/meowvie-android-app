package id.my.arieftb.data.model.response.movies.detail


import com.google.gson.annotations.SerializedName

data class BelongsToCollection(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("poster_path")
    val posterPath: Any? = null,
    @SerializedName("backdrop_path")
    val backdropPath: Any? = null
)