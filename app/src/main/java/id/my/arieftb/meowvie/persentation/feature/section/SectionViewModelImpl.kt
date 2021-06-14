package id.my.arieftb.meowvie.persentation.feature.section

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.my.arieftb.meowvie.constant.SectionType
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.usecase.movies.GetMoviesUseCase
import id.my.arieftb.meowvie.persentation.model.Data
import id.my.arieftb.meowvie.persentation.model.Status
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SectionViewModelImpl @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel(), SectionViewModel {
    override val contentData: MutableLiveData<Data<List<Content>>> = MutableLiveData()

    override fun getContents(page: Int, type: SectionType) {
        if (page == 0) {
            contentData.value = Data(Status.LOADING)
        }

        when (type) {
            SectionType.NEW_MOVIE -> getMovies(page)
        }
    }

    override fun getMovies(page: Int) {
        contentData.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            contentData.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = getMoviesUseCase.invoke(page = page)) {
                is Result.Success -> {
                    contentData.value = Data(Status.SUCCESS, result.data)
                }
                is Result.Failure -> {
                    contentData.value = Data(Status.ERROR, errorMessage = result.exception.message)
                }
            }
        }
    }
}