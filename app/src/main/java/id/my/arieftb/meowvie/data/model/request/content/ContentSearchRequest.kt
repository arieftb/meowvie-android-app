package id.my.arieftb.meowvie.data.model.request.content

import id.my.arieftb.meowvie.data.model.request.BaseRequest

class ContentSearchRequest(
    var keyword: String,
    var page: Int = 1,
    var language: String
): BaseRequest()