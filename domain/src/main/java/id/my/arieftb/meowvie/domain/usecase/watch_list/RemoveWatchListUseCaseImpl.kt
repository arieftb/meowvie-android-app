package id.my.arieftb.meowvie.domain.usecase.watch_list

import id.my.arieftb.meowvie.domain.constant.ContentType
import id.my.arieftb.meowvie.domain.model.entity.Result
import id.my.arieftb.meowvie.domain.repo.WatchListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoveWatchListUseCaseImpl @Inject constructor(
    private val repository: WatchListRepository
) : RemoveWatchListUseCase {
    override fun invoke(id: Long, type: ContentType): Flow<Result<Boolean>> {
        return repository.removeWatchList(id, type).flowOn(Dispatchers.IO)
    }
}