package id.my.arieftb.core.data.model.request.detail

import id.my.arieftb.core.data.model.request.BaseRequest

class DetailRequest(
    var id: Long = -1,
    var language: String = "en-US",
) : BaseRequest()