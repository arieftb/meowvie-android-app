package id.my.arieftb.meowvie.presentation.feature.section

import androidx.lifecycle.MutableLiveData
import id.my.arieftb.meowvie.constant.SectionType
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.presentation.model.Data

interface SectionViewModel {
    val contentData: MutableLiveData<Data<List<Content>>>

    fun getContents(page: Int = 1, type: SectionType)
    fun getMovies(page: Int = 1)
    fun getTvShows(page: Int = 1)
    fun getUpComingMovies(page: Int = 1)
    fun getUpComingTvShows(page: Int = 1)
    fun getPopularMovies(page: Int = 1)
    fun getPopularTvShows(page: Int = 1)
}