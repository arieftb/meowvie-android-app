package id.my.arieftb.meowvie.presentation.feature.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.Content
import id.my.arieftb.core.domain.usecase.contents.SearchContentsUseCase
import id.my.arieftb.meowvie.presentation.model.Data
import id.my.arieftb.meowvie.presentation.model.Status
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModelImpl @Inject constructor(
    private val searchContentsUseCase: SearchContentsUseCase
) : ViewModel(), ExploreViewModel {
    private var listData = mutableListOf<Content>()
    private val searchDataValue: MutableLiveData<Data<List<Content>>> = MutableLiveData()
    override val searchData: LiveData<Data<List<Content>>> = searchDataValue

    override fun search(page: Int, keyword: String) {
        if (page == 1) {
            listData.clear()
        }

        searchDataValue.value = Data(Status.LOADING)
        viewModelScope.launch {
            searchContentsUseCase.invoke(page, keyword).catch { cause: Throwable ->
                searchDataValue.value = Data(Status.ERROR, errorMessage = cause.localizedMessage)
            }.collect { result ->
                when (result) {
                    is ResultEntity.Failure -> searchDataValue.value =
                        Data(Status.ERROR, errorMessage = result.exception.localizedMessage)
                    is ResultEntity.Success -> searchDataValue.value = Data(Status.SUCCESS, result.data)
                }
            }
        }
    }
}