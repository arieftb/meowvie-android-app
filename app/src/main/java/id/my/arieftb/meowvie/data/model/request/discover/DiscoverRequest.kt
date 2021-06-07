package id.my.arieftb.meowvie.data.model.request.discover

import id.my.arieftb.meowvie.data.model.request.BaseRequest

class DiscoverRequest(
    var sortBy: String? = "release_date.desc",
    var page: Int = 1,
    var language: String = "en-US",
    var releaseDateLte: String? = null,
    var releaseDateGte: String? = null,
    var region: String = "ID"
): BaseRequest()