package id.my.arieftb.meowvie.data.model.response.tv_shows.detail


import com.google.gson.annotations.SerializedName

data class CreatedBy(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("credit_id")
    val creditId: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("gender")
    val gender: Int? = null,
    @SerializedName("profile_path")
    val profilePath: String? = null
)