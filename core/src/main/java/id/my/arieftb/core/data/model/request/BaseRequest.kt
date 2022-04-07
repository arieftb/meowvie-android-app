package id.my.arieftb.core.data.model.request

import id.my.arieftb.core.BuildConfig

open class BaseRequest(
    val apiKey: String = BuildConfig.API_KEY
)