package id.my.arieftb.meowvie.feature.home

import androidx.lifecycle.MutableLiveData
import id.my.arieftb.meowvie.domain.model.movie.Movie
import id.my.arieftb.meowvie.persentation.model.Data

interface HomeViewModel {
    val moviesData: MutableLiveData<Data<List<Movie>>>

    fun getMovies(page: Int)
}