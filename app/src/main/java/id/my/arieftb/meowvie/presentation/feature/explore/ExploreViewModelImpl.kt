package id.my.arieftb.meowvie.presentation.feature.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.usecase.contents.SearchContentsUseCase
import id.my.arieftb.meowvie.presentation.model.Data
import id.my.arieftb.meowvie.presentation.model.Status
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModelImpl @Inject constructor(
    private val searchContentsUseCase: SearchContentsUseCase
) : ViewModel(), ExploreViewModel {
    private var listData = mutableListOf<Content>()
    private val searchDataValue: MutableLiveData<Data<List<Content>>> = MutableLiveData()
    override val searchData : LiveData<Data<List<Content>>> = searchDataValue

    override fun search(page: Int, keyword: String) {
        if (page == 1) {
            listData.clear()
        }

        searchDataValue.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            searchDataValue.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = searchContentsUseCase.invoke(page = page, keyword)) {
                is Result.Success -> {
                    listData.addAll(result.data)
                    searchDataValue.value = Data(Status.SUCCESS, listData)
                }
                is Result.Failure -> searchDataValue.value =
                    Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }
}