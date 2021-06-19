package id.my.arieftb.meowvie.presentation.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.usecase.movies.highlight.GetMoviesHighlightUseCase
import id.my.arieftb.meowvie.domain.usecase.movies.popular.GetMoviesPopularHighlightUseCase
import id.my.arieftb.meowvie.domain.usecase.movies.upcoming.GetMoviesUpcomingHighlightUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.highlight.GetTvShowsHighlightUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.popular.GetTvShowsPopularHighlightUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.upcoming.GetTvShowsUpcomingHighlightUseCase
import id.my.arieftb.meowvie.presentation.model.Data
import id.my.arieftb.meowvie.presentation.model.Status
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val getMoviesHighlightUseCase: GetMoviesHighlightUseCase,
    private val getTvShowsHighlightUseCase: GetTvShowsHighlightUseCase,
    private val getMoviesUpcomingUseCase: GetMoviesUpcomingHighlightUseCase,
    private val getTvShowsUpcomingHighlightUseCase: GetTvShowsUpcomingHighlightUseCase,
    private val getMoviesPopularHighlightUseCase: GetMoviesPopularHighlightUseCase,
    private val getTvShowsPopularHighlightUseCase: GetTvShowsPopularHighlightUseCase
) :
    ViewModel(),
    HomeViewModel {
    private val moviesDataValue: MutableLiveData<Data<List<Content>>> = MutableLiveData()
    override var moviesData: LiveData<Data<List<Content>>> = moviesDataValue
    override val moviesUpcomingData: MutableLiveData<Data<List<Content>>> = MutableLiveData()
    override val moviesPopularData: MutableLiveData<Data<List<Content>>> = MutableLiveData()
    private val tvShowsDataValue: MutableLiveData<Data<List<Content>>> = MutableLiveData()
    override val tvShowsData: LiveData<Data<List<Content>>> = tvShowsDataValue
    override val tvShowsUpcomingData: MutableLiveData<Data<List<Content>>> = MutableLiveData()
    override val tvShowsPopularData: MutableLiveData<Data<List<Content>>> = MutableLiveData()

    override fun getMoviesHighlight() {
        moviesDataValue.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            moviesDataValue.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = getMoviesHighlightUseCase.invoke()) {
                is Result.Success -> {
                    moviesDataValue.value = Data(Status.SUCCESS, result.data)
                }
                is Result.Failure -> {
                    moviesDataValue.value = Data(Status.ERROR, errorMessage = result.exception.message)
                }
            }
        }
    }

    override fun getTvShowsHighlight() {
        tvShowsDataValue.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            tvShowsDataValue.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = getTvShowsHighlightUseCase.invoke()) {
                is Result.Success -> tvShowsDataValue.value = Data(Status.SUCCESS, result.data)
                is Result.Failure -> tvShowsDataValue.value =
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

    override fun getMoviesPopularHighlight() {
        moviesPopularData.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            moviesPopularData.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = getMoviesPopularHighlightUseCase.invoke()) {
                is Result.Success -> moviesPopularData.value = Data(Status.SUCCESS, result.data)
                is Result.Failure -> moviesPopularData.value =
                    Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }

    override fun getTvShowsPopularHighlight() {
        tvShowsPopularData.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            tvShowsPopularData.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = getTvShowsPopularHighlightUseCase.invoke()) {
                is Result.Success -> tvShowsPopularData.value = Data(Status.SUCCESS, result.data)
                is Result.Failure -> tvShowsPopularData.value =
                    Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }
}