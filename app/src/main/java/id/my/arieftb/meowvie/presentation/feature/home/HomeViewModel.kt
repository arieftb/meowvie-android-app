package id.my.arieftb.meowvie.presentation.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.presentation.model.Data

interface HomeViewModel {
    val moviesData: LiveData<Data<List<Content>>>
    val moviesUpcomingData: MutableLiveData<Data<List<Content>>>
    val moviesPopularData: MutableLiveData<Data<List<Content>>>
    val tvShowsData: LiveData<Data<List<Content>>>
    val tvShowsUpcomingData: MutableLiveData<Data<List<Content>>>
    val tvShowsPopularData: MutableLiveData<Data<List<Content>>>

    fun getMoviesHighlight()
    fun getTvShowsHighlight()
    fun getMoviesUpcomingHighlight()
    fun getTvShowsUpcomingHighlight()
    fun getMoviesPopularHighlight()
    fun getTvShowsPopularHighlight()
}