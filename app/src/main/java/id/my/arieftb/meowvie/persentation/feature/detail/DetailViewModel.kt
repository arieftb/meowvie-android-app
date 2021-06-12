package id.my.arieftb.meowvie.persentation.feature.detail

import androidx.lifecycle.LiveData
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.domain.model.movie.MovieDetail
import id.my.arieftb.meowvie.persentation.model.Data

interface DetailViewModel {
    var movieData: LiveData<Data<MovieDetail>>

    fun getDetail(id: Long, type: ContentType)
    fun getMovieDetail(id: Long)
}