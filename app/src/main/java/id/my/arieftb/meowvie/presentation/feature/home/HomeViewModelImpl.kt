package id.my.arieftb.meowvie.presentation.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.Content
import id.my.arieftb.core.domain.usecase.movies.highlight.GetMoviesHighlightUseCase
import id.my.arieftb.core.domain.usecase.movies.popular.GetMoviesPopularHighlightUseCase
import id.my.arieftb.core.domain.usecase.movies.upcoming.GetMoviesUpcomingHighlightUseCase
import id.my.arieftb.core.domain.usecase.tv_shows.highlight.GetTvShowsHighlightUseCase
import id.my.arieftb.core.domain.usecase.tv_shows.popular.GetTvShowsPopularHighlightUseCase
import id.my.arieftb.core.domain.usecase.tv_shows.upcoming.GetTvShowsUpcomingHighlightUseCase
import id.my.arieftb.meowvie.presentation.di.IoDispatcher
import id.my.arieftb.meowvie.presentation.model.Data
import id.my.arieftb.meowvie.presentation.model.Status
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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
        viewModelScope.launch {
            getMoviesHighlightUseCase.invoke().catch { cause: Throwable ->
                cause.printStackTrace()
                moviesDataValue.value = Data(Status.ERROR, errorMessage = cause.message)
            }.collect { result ->
                when (result) {
                    is ResultEntity.Failure -> moviesDataValue.value =
                        Data(Status.ERROR, errorMessage = result.exception.message)
                    is ResultEntity.Success -> moviesDataValue.value = Data(Status.SUCCESS, result.data)
                }
            }
        }
    }

    override fun getTvShowsHighlight() {
        tvShowsDataValue.value = Data(Status.LOADING)
        viewModelScope.launch {
            getTvShowsHighlightUseCase.invoke().catch { cause: Throwable ->
                tvShowsDataValue.value =
                    Data(Status.ERROR, errorMessage = cause.message)
            }.collect { value: ResultEntity<List<Content>> ->
                when (value) {
                    is ResultEntity.Success -> tvShowsDataValue.value = Data(Status.SUCCESS, value.data)
                    is ResultEntity.Failure -> tvShowsDataValue.value =
                        Data(Status.ERROR, errorMessage = value.exception.message)
                }
            }
        }
    }

    override fun getMoviesUpcomingHighlight() {
        moviesUpcomingDataValue.value = Data(Status.LOADING)
        viewModelScope.launch {
            getMoviesUpcomingUseCase.invoke().catch { cause: Throwable ->
                moviesUpcomingDataValue.value =
                    Data(Status.ERROR, errorMessage = cause.message)
            }.collect { value: ResultEntity<List<Content>> ->
                when (value) {
                    is ResultEntity.Success -> moviesUpcomingDataValue.value =
                        Data(Status.SUCCESS, value.data)
                    is ResultEntity.Failure -> moviesUpcomingDataValue.value =
                        Data(Status.ERROR, errorMessage = value.exception.message)
                }
            }

        }
    }

    override fun getTvShowsUpcomingHighlight() {
        tvShowsUpcomingDataValue.value = Data(Status.LOADING)
        viewModelScope.launch {
            getTvShowsUpcomingHighlightUseCase.invoke().catch { cause: Throwable ->
                tvShowsUpcomingDataValue.value =
                    Data(Status.ERROR, errorMessage = cause.message)
            }.collect { value: ResultEntity<List<Content>> ->
                when (value) {
                    is ResultEntity.Success -> tvShowsUpcomingDataValue.value =
                        Data(Status.SUCCESS, value.data)
                    is ResultEntity.Failure -> tvShowsUpcomingDataValue.value =
                        Data(Status.ERROR, errorMessage = value.exception.message)
                }
            }
        }
    }

    override fun getMoviesPopularHighlight() {
        moviesPopularDataValue.value = Data(Status.LOADING)
        viewModelScope.launch {
            getMoviesPopularHighlightUseCase.invoke().catch { cause: Throwable ->
                moviesPopularDataValue.value =
                    Data(Status.ERROR, errorMessage = cause.message)
            }.collect { value: ResultEntity<List<Content>> ->
                when (value) {
                    is ResultEntity.Success -> moviesPopularDataValue.value =
                        Data(Status.SUCCESS, value.data)
                    is ResultEntity.Failure -> moviesPopularDataValue.value =
                        Data(Status.ERROR, errorMessage = value.exception.message)
                }
            }
        }
    }

    override fun getTvShowsPopularHighlight() {
        tvShowsPopularDataValue.value = Data(Status.LOADING)
        viewModelScope.launch {
            getTvShowsPopularHighlightUseCase.invoke().catch { cause: Throwable ->
                tvShowsPopularDataValue.value =
                    Data(Status.ERROR, errorMessage = cause.message)
            }.collect { value: ResultEntity<List<Content>> ->
                when (value) {
                    is ResultEntity.Success -> tvShowsPopularDataValue.value =
                        Data(Status.SUCCESS, value.data)
                    is ResultEntity.Failure -> tvShowsPopularDataValue.value =
                        Data(Status.ERROR, errorMessage = value.exception.message)
                }
            }
        }
    }
}