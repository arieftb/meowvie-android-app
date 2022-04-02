package id.my.arieftb.meowvie.domain.usecase.watch_list

import androidx.paging.PagingData
import id.my.arieftb.meowvie.domain.model.entity.base.Content
import kotlinx.coroutines.flow.Flow

interface GetWatchListAllUseCase {
    fun invoke(limit: Int = 4): Flow<PagingData<Content>>
}