package id.my.arieftb.meowvie.data.model.request

import id.my.arieftb.meowvie.BuildConfig

open class BaseRequest(
    val apiKey: String = BuildConfig.API_KEY
)