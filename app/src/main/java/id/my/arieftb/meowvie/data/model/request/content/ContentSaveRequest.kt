package id.my.arieftb.meowvie.data.model.request.content

import id.my.arieftb.meowvie.domain.constant.ContentType

class ContentSaveRequest(
    var id: Long? = -1,
    var title: String? = null,
    var poster: String? = null,
    var banner: String? = null,
    var type: ContentType? = null,
    var createdAt: Long? = null
)