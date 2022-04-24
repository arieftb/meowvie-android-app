package id.my.arieftb.meowvie.presentation.feature.section

import androidx.lifecycle.LiveData
import id.my.arieftb.core.domain.constant.SectionType
import id.my.arieftb.core.domain.model.base.Content
import id.my.arieftb.meowvie.presentation.model.Data

interface SectionViewModel {
    val contentData: LiveData<Data<List<Content>>>

    fun getContents(page: Int = 1, type: SectionType)
    fun getMovies(page: Int = 1)
    fun getTvShows(page: Int = 1)
    fun getUpComingMovies(page: Int = 1)
    fun getUpComingTvShows(page: Int = 1)
    fun getPopularMovies(page: Int = 1)
    fun getPopularTvShows(page: Int = 1)
}