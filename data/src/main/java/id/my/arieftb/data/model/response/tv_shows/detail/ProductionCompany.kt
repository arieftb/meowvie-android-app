package id.my.arieftb.data.model.response.tv_shows.detail


import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("logo_path")
    val logoPath: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("origin_country")
    val originCountry: String? = null
)