package id.my.arieftb.meowvie.domain.model.movie

import id.my.arieftb.meowvie.domain.model.base.ContentDetail

class MovieDetail(
    override var id: Long,
    override var title: String
) : ContentDetail(
    id, title
)