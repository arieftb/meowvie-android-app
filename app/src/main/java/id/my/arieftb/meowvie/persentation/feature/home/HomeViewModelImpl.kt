package id.my.arieftb.meowvie.persentation.feature.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.movie.Movie
import id.my.arieftb.meowvie.domain.model.tv_show.TvShow
import id.my.arieftb.meowvie.domain.usecase.movies.GetMoviesHighlightUseCase
import id.my.arieftb.meowvie.domain.usecase.movies.GetMoviesUpcomingHighlightUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.GetTvShowsHighlightUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.GetTvShowsUpcomingHighlightUseCase
import id.my.arieftb.meowvie.persentation.model.Data
import id.my.arieftb.meowvie.persentation.model.Status
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val getMoviesHighlightUseCase: GetMoviesHighlightUseCase,
    private val getTvShowsHighlightUseCase: GetTvShowsHighlightUseCase,
    private val getMoviesUpcomingUseCase: GetMoviesUpcomingHighlightUseCase,
    private val getTvShowsUpcomingHighlightUseCase: GetTvShowsUpcomingHighlightUseCase
) :
    ViewModel(),
    HomeViewModel {
    override var moviesData: MutableLiveData<Data<List<Movie>>> = MutableLiveData()
    override val moviesUpcomingData: MutableLiveData<Data<List<Movie>>> = MutableLiveData()
    override val tvShowsData: MutableLiveData<Data<List<TvShow>>> = MutableLiveData()
    override val tvShowsUpcomingData: MutableLiveData<Data<List<TvShow>>> = MutableLiveData()

    override fun getMovies() {
        moviesData.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            moviesData.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = getMoviesHighlightUseCase.invoke()) {
                is Result.Success -> {
                    moviesData.value = Data(Status.SUCCESS, result.data)
                }
                is Result.Failure -> {
                    moviesData.value = Data(Status.ERROR, errorMessage = result.exception.message)
                }
            }
        }
    }

    override fun getTvShowsHighlight() {
        tvShowsData.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            tvShowsData.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = getTvShowsHighlightUseCase.invoke()) {
                is Result.Success -> tvShowsData.value = Data(Status.SUCCESS, result.data)
                is Result.Failure -> tvShowsData.value =
                    Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }

    override fun getMoviesUpcomingHighlight() {
        moviesUpcomingData.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            moviesUpcomingData.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = getMoviesUpcomingUseCase.invoke()) {
                is Result.Success -> moviesUpcomingData.value = Data(Status.SUCCESS, result.data)
                is Result.Failure -> moviesUpcomingData.value =
                    Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }

    override fun getTvShowsUpcomingHighlight() {
        tvShowsUpcomingData.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            tvShowsUpcomingData.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = getTvShowsUpcomingHighlightUseCase.invoke()) {
                is Result.Success -> tvShowsUpcomingData.value = Data(Status.SUCCESS, result.data)
                is Result.Failure -> tvShowsUpcomingData.value =
                    Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }
}