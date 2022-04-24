package id.my.arieftb.core.domain.model.tv_show

import id.my.arieftb.core.domain.model.base.Content

class TvShow(
    override var id: Long,
    override var title: String
) : Content(id, title)