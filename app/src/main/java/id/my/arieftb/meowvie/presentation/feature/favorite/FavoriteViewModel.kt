package id.my.arieftb.meowvie.presentation.feature.favorite

import androidx.paging.PagingData
import id.my.arieftb.core.domain.model.base.Content
import kotlinx.coroutines.flow.Flow

interface FavoriteViewModel {
    fun getWatchListAll(): Flow<PagingData<Content>>
}