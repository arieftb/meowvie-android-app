package id.my.arieftb.meowvie.persentation.feature.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.movie.MovieDetail
import id.my.arieftb.meowvie.domain.model.tv_show.TvShowDetail
import id.my.arieftb.meowvie.domain.usecase.movies.GetMovieDetailUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.GetTvShowDetailUseCase
import id.my.arieftb.meowvie.persentation.model.Data
import id.my.arieftb.meowvie.persentation.model.Status
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModelImpl @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getTvShowDetailUseCase: GetTvShowDetailUseCase
) :
    ViewModel(), DetailViewModel {
    override var movieData: MutableLiveData<Data<MovieDetail>> = MutableLiveData()
    override val tvShowData: MutableLiveData<Data<TvShowDetail>> = MutableLiveData()

    override fun getDetail(id: Long, type: ContentType) {
        when (type) {
            ContentType.TV_SHOW -> getTvShowDetail(id)
            else -> getMovieDetail(id)
        }
    }

    override fun getMovieDetail(id: Long) {
        movieData.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            movieData.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = getMovieDetailUseCase.invoke(id)) {
                is Result.Success -> movieData.value = Data(Status.SUCCESS, data = result.data)
                is Result.Failure -> movieData.value =
                    Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }

    override fun getTvShowDetail(id: Long) {
        tvShowData.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            tvShowData.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = getTvShowDetailUseCase.invoke(id)) {
                is Result.Success -> tvShowData.value = Data(Status.SUCCESS, data = result.data)
                is Result.Failure -> tvShowData.value =
                    Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }
}