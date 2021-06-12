package id.my.arieftb.meowvie.data.model.request.detail

import id.my.arieftb.meowvie.data.model.request.BaseRequest

class DetailRequest(
    var id: Long? = null,
    var language: String? = "en-US",
) : BaseRequest()