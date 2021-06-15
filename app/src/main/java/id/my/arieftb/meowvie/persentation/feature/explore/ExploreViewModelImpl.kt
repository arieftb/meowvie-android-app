package id.my.arieftb.meowvie.persentation.feature.explore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.usecase.contents.SearchContentsUseCase
import id.my.arieftb.meowvie.persentation.model.Data
import id.my.arieftb.meowvie.persentation.model.Status
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModelImpl @Inject constructor(
    private val searchContentsUseCase: SearchContentsUseCase
) : ViewModel(), ExploreViewModel {
    private var listData = mutableListOf<Content>()
    override val searchData: MutableLiveData<Data<List<Content>>> = MutableLiveData()

    override fun search(page: Int, keyword: String) {
        if (page == 1) {
            listData.clear()
        }

        searchData.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            searchData.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = searchContentsUseCase.invoke(page = page, keyword)) {
                is Result.Success -> {
                    listData.addAll(result.data)
                    searchData.value = Data(Status.SUCCESS, listData)
                }
                is Result.Failure -> searchData.value =
                    Data(Status.ERROR, errorMessage = result.exception.message)
            }
        }
    }
}