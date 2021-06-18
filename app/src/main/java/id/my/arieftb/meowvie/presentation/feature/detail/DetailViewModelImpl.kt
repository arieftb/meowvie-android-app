package id.my.arieftb.meowvie.presentation.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.model.base.ContentDetail
import id.my.arieftb.meowvie.domain.usecase.movies.detail.GetMovieDetailUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.detail.GetTvShowDetailUseCase
import id.my.arieftb.meowvie.domain.usecase.watch_list.CheckWatchListUseCase
import id.my.arieftb.meowvie.domain.usecase.watch_list.RemoveWatchListUseCase
import id.my.arieftb.meowvie.domain.usecase.watch_list.SaveWatchListUseCase
import id.my.arieftb.meowvie.presentation.model.Data
import id.my.arieftb.meowvie.presentation.model.Status
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModelImpl @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getTvShowDetailUseCase: GetTvShowDetailUseCase,
    private val saveWatchListUseCase: SaveWatchListUseCase,
    private val checkWatchListUseCase: CheckWatchListUseCase,
    private val removeWatchListUseCase: RemoveWatchListUseCase
) :
    ViewModel(), DetailViewModel {
    private val detailDataValue: MutableLiveData<Data<ContentDetail>> = MutableLiveData()
    override var detailData: LiveData<Data<ContentDetail>> = detailDataValue
    override val isSaved: MutableLiveData<Data<Boolean>> = MutableLiveData()
    private val isAvailableValue: MutableLiveData<Data<Boolean>> = MutableLiveData()
    override val isAvailable: LiveData<Data<Boolean>> = isAvailableValue

    override fun getDetail(id: Long, type: ContentType) {
        when (type) {
            ContentType.TV -> getTvShowDetail(id)
            else -> getMovieDetail(id)
        }
    }

    override fun getMovieDetail(id: Long) {
        detailDataValue.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            detailDataValue.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = getMovieDetailUseCase.invoke(id)) {
                is Result.Success -> detailDataValue.value = Data(Status.SUCCESS, data = result.data)
                is Result.Failure -> detailDataValue.value =
                    Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }

    override fun getTvShowDetail(id: Long) {
        detailDataValue.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            detailDataValue.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = getTvShowDetailUseCase.invoke(id)) {
                is Result.Success -> detailDataValue.value = Data(Status.SUCCESS, data = result.data)
                is Result.Failure -> detailDataValue.value =
                    Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }

    override fun checkWatchList(code: Long, type: ContentType) {
        isAvailableValue.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            isAvailableValue.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = checkWatchListUseCase.invoke(code, type)) {
                is Result.Success -> isAvailableValue.value = Data(Status.SUCCESS, data = result.data)
                is Result.Failure -> isAvailableValue.value =
                    Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }

    override fun saveWatchList(content: Content) {
        isSaved.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            isSaved.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = saveWatchListUseCase.invoke(content)) {
                is Result.Success -> isSaved.value = Data(Status.SUCCESS, data = result.data)
                is Result.Failure -> isSaved.value =
                    Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }

    override fun removeContent(code: Long, type: ContentType) {
        isSaved.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            isSaved.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = removeWatchListUseCase.invoke(code, type)) {
                is Result.Success -> isSaved.value = Data(Status.SUCCESS, data = result.data)
                is Result.Failure -> isSaved.value =
                    Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }

}