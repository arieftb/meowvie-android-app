package id.my.arieftb.meowvie.persentation.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.domain.model.movie.MovieDetail
import id.my.arieftb.meowvie.persentation.model.Data

class DetailViewModelImpl : ViewModel(), DetailViewModel {
    override var movieData: LiveData<Data<MovieDetail>> = MutableLiveData()

    override fun getDetail(id: Long, type: ContentType) {
        when (type) {
            ContentType.TV_SHOW -> {
            }
            else -> getMovieDetail(id)
        }
    }

    override fun getMovieDetail(id: Long) {

    }
}