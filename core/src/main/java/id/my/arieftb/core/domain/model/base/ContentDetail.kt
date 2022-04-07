package id.my.arieftb.core.domain.model.base

open class ContentDetail(
    open var id: Long,
    open var title: String,
) {
    var posterPath: String? = null
    var bannerPath: String? = null
    var releaseDate: String = ""
    var overview: String = ""
    var genre: String = ""
    var rating: Double = 0.0
}