package id.my.arieftb.meowvie.persentation.feature.detail

import androidx.lifecycle.MutableLiveData
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.domain.model.movie.MovieDetail
import id.my.arieftb.meowvie.domain.model.tv_show.TvShowDetail
import id.my.arieftb.meowvie.persentation.model.Data

interface DetailViewModel {
    val movieData: MutableLiveData<Data<MovieDetail>>
    val tvShowData: MutableLiveData<Data<TvShowDetail>>

    fun getDetail(id: Long, type: ContentType)
    fun getMovieDetail(id: Long)
    fun getTvShowDetail(id: Long)
}