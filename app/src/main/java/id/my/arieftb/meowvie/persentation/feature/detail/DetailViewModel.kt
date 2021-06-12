package id.my.arieftb.meowvie.persentation.feature.detail

import id.my.arieftb.meowvie.constant.ContentType

interface DetailViewModel {
    fun getDetail(id: String, type: ContentType)
}