package id.my.arieftb.core.domain.model.movie

import id.my.arieftb.core.domain.model.base.Content

class Movie(
    override var id: Long,
    override var title: String
) : Content(id, title)