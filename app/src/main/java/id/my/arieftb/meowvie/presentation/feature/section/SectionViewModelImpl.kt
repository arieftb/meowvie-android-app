package id.my.arieftb.meowvie.presentation.feature.section

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.my.arieftb.core.domain.constant.SectionType
import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.Content
import id.my.arieftb.core.domain.usecase.movies.GetMoviesUseCase
import id.my.arieftb.core.domain.usecase.movies.popular.GetMoviesPopularUseCase
import id.my.arieftb.core.domain.usecase.movies.upcoming.GetMoviesUpcomingUseCase
import id.my.arieftb.core.domain.usecase.tv_shows.GetTvShowsUseCase
import id.my.arieftb.core.domain.usecase.tv_shows.popular.GetTvShowsPopularUseCase
import id.my.arieftb.core.domain.usecase.tv_shows.upcoming.GetTvShowsUpcomingUseCase
import id.my.arieftb.meowvie.presentation.di.IoDispatcher
import id.my.arieftb.meowvie.presentation.model.Data
import id.my.arieftb.meowvie.presentation.model.Status
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SectionViewModelImpl @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getTvShowsUseCase: GetTvShowsUseCase,
    private val getMoviesUpcomingUseCase: GetMoviesUpcomingUseCase,
    private val getTvShowsUpcomingUseCase: GetTvShowsUpcomingUseCase,
    private val getMoviesPopularUseCase: GetMoviesPopularUseCase,
    private val getTvShowsPopularUseCase: GetTvShowsPopularUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel(), SectionViewModel {
    private val listData = mutableListOf<Content>()

    private val contentDataValue: MutableLiveData<Data<List<Content>>> = MutableLiveData()
    override val contentData: LiveData<Data<List<Content>>> = contentDataValue

    override fun getContents(page: Int, type: SectionType) {
        if (page == 1) {
            contentDataValue.value = Data(Status.LOADING)
        }

        when (type) {
            SectionType.NEW_MOVIE -> getMovies(page)
            SectionType.NEW_TV -> getTvShows(page)
            SectionType.UPCOMING_MOVIE -> getUpComingMovies(page)
            SectionType.UPCOMING_TV -> getUpComingTvShows(page)
            SectionType.POPULAR_MOVIE -> getPopularMovies(page)
            SectionType.POPULAR_TV -> getPopularTvShows(page)
            else -> contentDataValue.value = Data(Status.SUCCESS, emptyList())
        }
    }

    override fun getMovies(page: Int) {
        viewModelScope.launch {
            getMoviesUseCase.invoke(page).catch { cause: Throwable ->
                contentDataValue.value = Data(Status.ERROR, errorMessage = cause.message)
            }.collect { result ->
                when (result) {
                    is ResultEntity.Failure -> contentDataValue.value =
                        Data(Status.ERROR, errorMessage = result.exception.message)
                    is ResultEntity.Success -> contentDataValue.value = Data(Status.SUCCESS, result.data)
                }
            }
        }
    }

    override fun getTvShows(page: Int) {
        viewModelScope.launch {
            getTvShowsUseCase.invoke(page).catch { cause: Throwable ->
                contentDataValue.value = Data(Status.ERROR, errorMessage = cause.message)
            }.collect { value: ResultEntity<List<Content>> ->
                when (value) {
                    is ResultEntity.Failure -> contentDataValue.value =
                        Data(Status.ERROR, errorMessage = value.exception.message)
                    is ResultEntity.Success -> setValueSuccess(value.data)
                }
            }
        }
    }

    override fun getUpComingMovies(page: Int) {
        viewModelScope.launch {
            getMoviesUpcomingUseCase.invoke(page).catch { cause: Throwable ->
                contentDataValue.value = Data(Status.ERROR, errorMessage = cause.message)
            }.collect { result ->
                when (result) {
                    is ResultEntity.Failure -> contentDataValue.value =
                        Data(Status.ERROR, errorMessage = result.exception.message)
                    is ResultEntity.Success -> setValueSuccess(result.data)
                }
            }
        }
    }

    override fun getUpComingTvShows(page: Int) {
        viewModelScope.launch {
            getTvShowsUpcomingUseCase.invoke(page).catch { cause: Throwable ->
                contentDataValue.value =
                    Data(Status.ERROR, errorMessage = cause.message)
            }.collect { value: ResultEntity<List<Content>> ->
                when (value) {
                    is ResultEntity.Success -> setValueSuccess(value.data)
                    is ResultEntity.Failure -> contentDataValue.value =
                        Data(Status.ERROR, errorMessage = value.exception.message)
                }
            }
        }
    }


    override fun getPopularMovies(page: Int) {
        viewModelScope.launch {
            getMoviesPopularUseCase.invoke(page).catch { cause: Throwable ->
                contentDataValue.value = Data(Status.ERROR, errorMessage = cause.message)
            }.collect { value: ResultEntity<List<Content>> ->
                when (value) {
                    is ResultEntity.Failure -> contentDataValue.value =
                        Data(Status.ERROR, errorMessage = value.exception.message)
                    is ResultEntity.Success -> setValueSuccess(value.data)
                }
            }
        }
    }

    override fun getPopularTvShows(page: Int) {
        viewModelScope.launch {
            getTvShowsPopularUseCase.invoke(page).catch { cause: Throwable ->
                contentDataValue.value =
                    Data(Status.ERROR, errorMessage = cause.message)
            }.collect { value: ResultEntity<List<Content>> ->
                when (value) {
                    is ResultEntity.Success -> setValueSuccess(value.data)
                    is ResultEntity.Failure -> contentDataValue.value =
                        Data(Status.ERROR, errorMessage = value.exception.message)
                }
            }
        }
    }

    private fun setValueSuccess(list: List<Content>) {
        listData.addAll(list)
        contentDataValue.value = Data(Status.SUCCESS, listData)
    }
}