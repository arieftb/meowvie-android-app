package id.my.arieftb.meowvie.presentation.feature.explore

import androidx.lifecycle.MutableLiveData
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.presentation.model.Data

interface ExploreViewModel {
    val searchData: MutableLiveData<Data<List<Content>>>

    fun search(page: Int = 1, keyword: String)
}