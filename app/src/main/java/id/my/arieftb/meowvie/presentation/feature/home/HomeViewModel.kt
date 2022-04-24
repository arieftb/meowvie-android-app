package id.my.arieftb.meowvie.presentation.feature.home

import androidx.lifecycle.LiveData
import id.my.arieftb.core.domain.model.base.Content
import id.my.arieftb.meowvie.presentation.model.Data

interface HomeViewModel {
    val moviesData: LiveData<Data<List<Content>>>
    val moviesUpcomingData: LiveData<Data<List<Content>>>
    val moviesPopularData: LiveData<Data<List<Content>>>
    val tvShowsData: LiveData<Data<List<Content>>>
    val tvShowsUpcomingData: LiveData<Data<List<Content>>>
    val tvShowsPopularData: LiveData<Data<List<Content>>>

    fun getMoviesHighlight()
    fun getTvShowsHighlight()
    fun getMoviesUpcomingHighlight()
    fun getTvShowsUpcomingHighlight()
    fun getMoviesPopularHighlight()
    fun getTvShowsPopularHighlight()
}