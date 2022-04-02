package id.my.arieftb.meowvie.domain.model.entity.base

import id.my.arieftb.meowvie.domain.constant.ContentType

open class Content(
    open var id: Long,
    open var title: String,
) {
    var bannerPath: String? = null
    var posterPath: String? = null
    var type: ContentType = ContentType.MOVIE
}