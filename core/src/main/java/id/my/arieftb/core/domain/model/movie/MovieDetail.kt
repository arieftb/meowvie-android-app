package id.my.arieftb.core.domain.model.movie

import id.my.arieftb.core.domain.model.base.ContentDetail

class MovieDetail(
    override var id: Long,
    override var title: String
) : ContentDetail(
    id, title
)