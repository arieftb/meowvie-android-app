package id.my.arieftb.meowvie.domain.usecase.watch_list

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.PagingData
import id.my.arieftb.meowvie.data.model.entity.WatchListEntity
import id.my.arieftb.meowvie.domain.repo.WatchListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWatchListAllUseCaseImpl @Inject constructor(
    private val repository: WatchListRepository
) : GetWatchListAllUseCase {
    override fun invoke(limit: Int): Flow<PagingData<WatchListEntity>> {
        return repository.fetchAllWatchList(limit)
    }
}