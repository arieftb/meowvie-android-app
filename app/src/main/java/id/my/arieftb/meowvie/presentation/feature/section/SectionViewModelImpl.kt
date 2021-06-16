package id.my.arieftb.meowvie.presentation.feature.section

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.my.arieftb.meowvie.constant.SectionType
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.usecase.movies.GetMoviesUseCase
import id.my.arieftb.meowvie.domain.usecase.movies.popular.GetMoviesPopularUseCase
import id.my.arieftb.meowvie.domain.usecase.movies.upcoming.GetMoviesUpcomingUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.GetTvShowsUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.popular.GetTvShowsPopularUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.upcoming.GetTvShowsUpcomingUseCase
import id.my.arieftb.meowvie.presentation.model.Data
import id.my.arieftb.meowvie.presentation.model.Status
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SectionViewModelImpl @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getTvShowsUseCase: GetTvShowsUseCase,
    private val getMoviesUpcomingUseCase: GetMoviesUpcomingUseCase,
    private val getTvShowsUpcomingUseCase: GetTvShowsUpcomingUseCase,
    private val getMoviesPopularUseCase: GetMoviesPopularUseCase,
    private val getTvShowsPopularUseCase: GetTvShowsPopularUseCase
) : ViewModel(), SectionViewModel {
    private val listData = mutableListOf<Content>()

    override val contentData: MutableLiveData<Data<List<Content>>> = MutableLiveData()

    override fun getContents(page: Int, type: SectionType) {
        if (page == 0) {
            contentData.value = Data(Status.LOADING)
        }

        when (type) {
            SectionType.NEW_TV -> getTvShows(page)
            SectionType.UPCOMING_MOVIE -> getUpComingMovies(page)
            SectionType.UPCOMING_TV -> getUpComingTvShows(page)
            SectionType.POPULAR_MOVIE -> getPopularMovies(page)
            SectionType.POPULAR_TV -> getPopularTvShows(page)
            else -> getMovies(page)
        }
    }

    override fun getMovies(page: Int) {
        contentData.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            contentData.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = getMoviesUseCase.invoke(page = page)) {
                is Result.Success -> setValueSuccess(result.data)
                is Result.Failure -> contentData.value = Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }

    override fun getTvShows(page: Int) {
        contentData.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            contentData.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = getTvShowsUseCase.invoke(page = page)) {
                is Result.Success -> setValueSuccess(result.data)
                is Result.Failure -> contentData.value = Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }

    override fun getUpComingMovies(page: Int) {
        contentData.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            contentData.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = getMoviesUpcomingUseCase.invoke(page = page)) {
                is Result.Success -> setValueSuccess(result.data)
                is Result.Failure -> contentData.value = Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }

    override fun getUpComingTvShows(page: Int) {
        contentData.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            contentData.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = getTvShowsUpcomingUseCase.invoke(page = page)) {
                is Result.Success -> setValueSuccess(result.data)
                is Result.Failure -> contentData.value = Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }

    override fun getPopularMovies(page: Int) {
        contentData.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            contentData.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = getMoviesPopularUseCase.invoke(page = page)) {
                is Result.Success -> setValueSuccess(result.data)
                is Result.Failure -> contentData.value = Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }

    override fun getPopularTvShows(page: Int) {
        contentData.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            contentData.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = getTvShowsPopularUseCase.invoke(page = page)) {
                is Result.Success -> setValueSuccess(result.data)
                is Result.Failure -> contentData.value = Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }

    private fun setValueSuccess(list: List<Content>) {
        listData.addAll(list)
        contentData.value = Data(Status.SUCCESS, listData)
    }
}