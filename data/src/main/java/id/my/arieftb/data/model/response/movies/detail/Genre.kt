package id.my.arieftb.data.model.response.movies.detail


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("name")
    val name: String? = null
)