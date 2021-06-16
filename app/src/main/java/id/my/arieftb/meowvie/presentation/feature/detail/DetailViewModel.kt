package id.my.arieftb.meowvie.presentation.feature.detail

import androidx.lifecycle.MutableLiveData
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.model.base.ContentDetail
import id.my.arieftb.meowvie.presentation.model.Data

interface DetailViewModel {
    val detailData: MutableLiveData<Data<ContentDetail>>
    val isSaved: MutableLiveData<Data<Boolean>>
    val isAvailable: MutableLiveData<Data<Boolean>>

    fun getDetail(id: Long, type: ContentType)
    fun getMovieDetail(id: Long)
    fun getTvShowDetail(id: Long)
    fun checkWatchList(code: Long, type: ContentType)
    fun saveWatchList(content: Content)
    fun removeContent(code: Long, type: ContentType)
}