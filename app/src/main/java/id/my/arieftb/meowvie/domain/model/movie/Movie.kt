package id.my.arieftb.meowvie.domain.model.movie

import id.my.arieftb.meowvie.domain.model.base.Content

class Movie(
    override var id: Long,
    override var title: String
) : Content(id, title)