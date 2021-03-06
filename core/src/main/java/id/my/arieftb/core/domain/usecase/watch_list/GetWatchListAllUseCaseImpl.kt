package id.my.arieftb.core.domain.usecase.watch_list

import androidx.paging.PagingData
import id.my.arieftb.core.domain.model.base.Content
import id.my.arieftb.core.domain.repo.WatchListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWatchListAllUseCaseImpl @Inject constructor(
    private val repository: WatchListRepository
) : GetWatchListAllUseCase {
    override fun invoke(limit: Int): Flow<PagingData<Content>> {
        return repository.fetchAllWatchList(limit)
    }
}