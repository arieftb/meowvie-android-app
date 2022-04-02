package id.my.arieftb.meowvie.presentation.feature.detail

import androidx.lifecycle.LiveData
import id.my.arieftb.meowvie.domain.constant.ContentType
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.model.base.ContentDetail
import id.my.arieftb.meowvie.presentation.model.Data

interface DetailViewModel {
    val detailData: LiveData<Data<ContentDetail>>
    val isSaved: LiveData<Data<Boolean>>
    val isAvailable: LiveData<Data<Boolean>>

    fun getDetail(id: Long, type: ContentType)
    fun getMovieDetail(id: Long)
    fun getTvShowDetail(id: Long)
    fun checkWatchList(code: Long, type: ContentType)
    fun saveWatchList(content: Content)
    fun removeContent(code: Long, type: ContentType)
}