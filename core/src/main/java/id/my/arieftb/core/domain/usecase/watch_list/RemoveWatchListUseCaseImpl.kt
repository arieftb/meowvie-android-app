package id.my.arieftb.core.domain.usecase.watch_list

import id.my.arieftb.core.domain.constant.ContentType
import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.repo.WatchListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoveWatchListUseCaseImpl @Inject constructor(
    private val repository: WatchListRepository
) : RemoveWatchListUseCase {
    override fun invoke(id: Long, type: ContentType): Flow<ResultEntity<Boolean>> {
        return repository.removeWatchList(id, type).flowOn(Dispatchers.IO)
    }
}