package id.my.arieftb.core.domain.model.base

import id.my.arieftb.core.domain.constant.ContentType

open class Content(
    open var id: Long,
    open var title: String,
) {
    var bannerPath: String? = null
    var posterPath: String? = null
    var type: ContentType = ContentType.MOVIE
}