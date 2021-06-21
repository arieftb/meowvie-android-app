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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    private val isSavedValue: MutableLiveData<Data<Boolean>> = MutableLiveData()
    override val isSaved: LiveData<Data<Boolean>> = isSavedValue
    private val isAvailableValue: MutableLiveData<Data<Boolean>> = MutableLiveData()
    override val isAvailable: LiveData<Data<Boolean>> = isAvailableValue

    override fun getDetail(id: Long, type: ContentType) {
        when (type) {
            ContentType.TV -> getTvShowDetail(id)
            else -> getMovieDetail(id)
        }
    }

    override fun getMovieDetail(id: Long) {
        detailDataValue.postValue(Data(Status.LOADING))
        viewModelScope.launch {
            try {
                when (val result = withContext(Dispatchers.IO) {
                    getMovieDetailUseCase.invoke(id)
                }) {
                    is Result.Success -> {
                        detailDataValue.postValue(
                            Data(
                                Status.SUCCESS,
                                data = result.data
                            )
                        )
                    }
                    is Result.Failure -> {
                        detailDataValue.postValue(
                            Data(
                                Status.ERROR,
                                errorMessage = result.exception.message
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                detailDataValue.postValue(Data(Status.ERROR, errorMessage = e.message))
            }
        }
    }

    override fun getTvShowDetail(id: Long) {
        detailDataValue.postValue(Data(Status.LOADING))
        viewModelScope.launch {
            try {
                when (val result = withContext(Dispatchers.IO) {
                    getTvShowDetailUseCase.invoke(id)
                }) {
                    is Result.Success -> detailDataValue.postValue(
                        Data(
                            Status.SUCCESS,
                            data = result.data
                        )
                    )
                    is Result.Failure -> detailDataValue.postValue(
                        Data(
                            Status.ERROR,
                            errorMessage = result.exception.message
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                detailDataValue.postValue(Data(Status.ERROR, errorMessage = e.message))
            }
        }
    }

    override fun checkWatchList(code: Long, type: ContentType) {
        isAvailableValue.postValue(Data(Status.LOADING))
        viewModelScope.launch {
            try {
                when (val result = withContext(Dispatchers.IO) {
                    checkWatchListUseCase.invoke(code, type)
                }) {
                    is Result.Success -> isAvailableValue.postValue(
                        Data(
                            Status.SUCCESS,
                            data = result.data
                        )
                    )
                    is Result.Failure -> isAvailableValue.postValue(
                        Data(
                            Status.ERROR,
                            errorMessage = result.exception.message
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                isAvailableValue.postValue(Data(Status.ERROR, errorMessage = e.message))
            }
        }
    }

    override fun saveWatchList(content: Content) {
        isSavedValue.postValue(Data(Status.LOADING))
        viewModelScope.launch {
            try {
                when (val result = withContext(Dispatchers.IO) {
                    saveWatchListUseCase.invoke(content)
                }) {
                    is Result.Success -> isSavedValue.postValue(
                        Data(
                            Status.SUCCESS,
                            data = result.data
                        )
                    )
                    is Result.Failure -> isSavedValue.postValue(
                        Data(
                            Status.ERROR,
                            errorMessage = result.exception.message
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                isSavedValue.postValue(Data(Status.ERROR, errorMessage = e.message))
            }
        }
    }

    override fun removeContent(code: Long, type: ContentType) {
        isSavedValue.value = Data(Status.LOADING)
        viewModelScope.launch {
            try {
                when (val result = withContext(Dispatchers.IO) {
                    removeWatchListUseCase.invoke(code, type)
                }) {
                    is Result.Success -> isSavedValue.postValue(
                        Data(
                            Status.SUCCESS,
                            data = result.data
                        )
                    )
                    is Result.Failure -> isSavedValue.postValue(
                        Data(
                            Status.ERROR,
                            errorMessage = result.exception.message
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                isSavedValue.postValue(Data(Status.ERROR, errorMessage = e.message))
            }
        }
    }

}