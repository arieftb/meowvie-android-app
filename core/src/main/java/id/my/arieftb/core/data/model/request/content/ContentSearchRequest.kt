package id.my.arieftb.core.data.model.request.content

import id.my.arieftb.core.data.model.request.BaseRequest

class ContentSearchRequest(
    var keyword: String,
    var page: Int = 1,
    var language: String
): BaseRequest()