package id.my.arieftb.meowvie.persentation.feature.detail

import androidx.lifecycle.MutableLiveData
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.model.base.ContentDetail
import id.my.arieftb.meowvie.persentation.model.Data

interface DetailViewModel {
    val detailData: MutableLiveData<Data<ContentDetail>>
    val isSaved: MutableLiveData<Data<Boolean>>

    fun getDetail(id: Long, type: ContentType)
    fun getMovieDetail(id: Long)
    fun getTvShowDetail(id: Long)
    fun saveContent(content: Content)
    fun saveMovie(content: Content)
    fun saveTvShow(content: Content)
}