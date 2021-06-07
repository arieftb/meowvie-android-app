package id.my.arieftb.meowvie.persentation.feature.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.movie.Movie
import id.my.arieftb.meowvie.domain.usecase.movies.GetMoviesHighlightUseCase
import id.my.arieftb.meowvie.persentation.model.Data
import id.my.arieftb.meowvie.persentation.model.Status
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(private val getMoviesHighlightUseCase: GetMoviesHighlightUseCase) :
    ViewModel(),
    HomeViewModel {
    override var moviesData: MutableLiveData<Data<List<Movie>>> = MutableLiveData()

    override fun getMovies() {
        moviesData.value = Data(Status.LOADING)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            moviesData.value = Data(Status.ERROR, errorMessage = throwable.message)
        }) {
            when (val result = getMoviesHighlightUseCase.invoke()) {
                is Result.Success -> {
                    moviesData.value = Data(Status.SUCCESS, result.data)
                }
                is Result.Failure -> {
                    moviesData.value = Data(Status.ERROR, errorMessage = result.exception.message)
                }
            }
        }
    }
}