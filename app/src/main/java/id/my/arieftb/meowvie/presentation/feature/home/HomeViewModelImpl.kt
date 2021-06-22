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
import id.my.arieftb.meowvie.presentation.di.IoDispatcher
import id.my.arieftb.meowvie.presentation.model.Data
import id.my.arieftb.meowvie.presentation.model.Status
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val getMoviesHighlightUseCase: GetMoviesHighlightUseCase,
    private val getTvShowsHighlightUseCase: GetTvShowsHighlightUseCase,
    private val getMoviesUpcomingUseCase: GetMoviesUpcomingHighlightUseCase,
    private val getTvShowsUpcomingHighlightUseCase: GetTvShowsUpcomingHighlightUseCase,
    private val getMoviesPopularHighlightUseCase: GetMoviesPopularHighlightUseCase,
    private val getTvShowsPopularHighlightUseCase: GetTvShowsPopularHighlightUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) :
    ViewModel(),
    HomeViewModel {
    private val moviesDataValue: MutableLiveData<Data<List<Content>>> = MutableLiveData()
    override val moviesData: LiveData<Data<List<Content>>> = moviesDataValue
    private val moviesUpcomingDataValue: MutableLiveData<Data<List<Content>>> = MutableLiveData()
    override val moviesUpcomingData: LiveData<Data<List<Content>>> = moviesUpcomingDataValue
    private val moviesPopularDataValue: MutableLiveData<Data<List<Content>>> = MutableLiveData()
    override val moviesPopularData: LiveData<Data<List<Content>>> = moviesPopularDataValue
    private val tvShowsDataValue: MutableLiveData<Data<List<Content>>> = MutableLiveData()
    override val tvShowsData: LiveData<Data<List<Content>>> = tvShowsDataValue
    private val tvShowsUpcomingDataValue: MutableLiveData<Data<List<Content>>> = MutableLiveData()
    override val tvShowsUpcomingData: LiveData<Data<List<Content>>> = tvShowsUpcomingDataValue
    private val tvShowsPopularDataValue: MutableLiveData<Data<List<Content>>> = MutableLiveData()
    override val tvShowsPopularData: LiveData<Data<List<Content>>> = tvShowsPopularDataValue

    override fun getMoviesHighlight() {
        moviesDataValue.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            moviesDataValue.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = withContext(dispatcher) {
                getMoviesHighlightUseCase.invoke()
            }) {
                is Result.Success -> {
                    moviesDataValue.value = Data(Status.SUCCESS, result.data)
                }
                is Result.Failure -> {
                    moviesDataValue.value =
                        Data(Status.ERROR, errorMessage = result.exception.message)
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
            when (val result = withContext(dispatcher) {
                getTvShowsHighlightUseCase.invoke()
            }) {
                is Result.Success -> tvShowsDataValue.value = Data(Status.SUCCESS, result.data)
                is Result.Failure -> tvShowsDataValue.value =
                    Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }

    override fun getMoviesUpcomingHighlight() {
        moviesUpcomingDataValue.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            moviesUpcomingDataValue.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = withContext(dispatcher) {
                getMoviesUpcomingUseCase.invoke()
            }) {
                is Result.Success -> moviesUpcomingDataValue.value =
                    Data(Status.SUCCESS, result.data)
                is Result.Failure -> moviesUpcomingDataValue.value =
                    Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }

    override fun getTvShowsUpcomingHighlight() {
        tvShowsUpcomingDataValue.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            tvShowsUpcomingDataValue.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = withContext(dispatcher) {
                getTvShowsUpcomingHighlightUseCase.invoke()
            }) {
                is Result.Success -> tvShowsUpcomingDataValue.value =
                    Data(Status.SUCCESS, result.data)
                is Result.Failure -> tvShowsUpcomingDataValue.value =
                    Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }

    override fun getMoviesPopularHighlight() {
        moviesPopularDataValue.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            moviesPopularDataValue.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = withContext(dispatcher) {
                getMoviesPopularHighlightUseCase.invoke()
            }) {
                is Result.Success -> moviesPopularDataValue.value =
                    Data(Status.SUCCESS, result.data)
                is Result.Failure -> moviesPopularDataValue.value =
                    Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }

    override fun getTvShowsPopularHighlight() {
        tvShowsPopularDataValue.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            tvShowsPopularDataValue.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = withContext(dispatcher) {
                getTvShowsPopularHighlightUseCase.invoke()
            }) {
                is Result.Success -> tvShowsPopularDataValue.value =
                    Data(Status.SUCCESS, result.data)
                is Result.Failure -> tvShowsPopularDataValue.value =
                    Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }
}