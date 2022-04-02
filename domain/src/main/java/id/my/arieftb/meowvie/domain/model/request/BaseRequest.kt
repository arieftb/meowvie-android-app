package id.my.arieftb.meowvie.domain.model.request

import id.my.arieftb.meowvie.domain.BuildConfig

open class BaseRequest(
    val apiKey: String = BuildConfig.API_KEY
)