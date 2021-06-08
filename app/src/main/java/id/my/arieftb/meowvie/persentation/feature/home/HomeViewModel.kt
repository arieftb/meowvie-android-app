package id.my.arieftb.meowvie.persentation.feature.home

import androidx.lifecycle.MutableLiveData
import id.my.arieftb.meowvie.domain.model.movie.Movie
import id.my.arieftb.meowvie.domain.model.tv_show.TvShow
import id.my.arieftb.meowvie.persentation.model.Data

interface HomeViewModel {
    val moviesData: MutableLiveData<Data<List<Movie>>>
    val moviesUpcomingData: MutableLiveData<Data<List<Movie>>>
    val tvShowsData: MutableLiveData<Data<List<TvShow>>>
    val tvShowsUpcomingData : MutableLiveData<Data<List<TvShow>>>

    fun getMovies()
    fun getTvShowsHighlight()
    fun getMoviesUpcomingHighlight()
    fun getTvShowsUpcomingHighlight()
}