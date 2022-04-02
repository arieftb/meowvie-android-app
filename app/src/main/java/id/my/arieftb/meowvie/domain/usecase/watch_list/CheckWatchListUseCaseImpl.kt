package id.my.arieftb.meowvie.domain.usecase.watch_list

import id.my.arieftb.meowvie.domain.constant.ContentType
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.repo.WatchListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CheckWatchListUseCaseImpl @Inject constructor(
    private val repository: WatchListRepository
) : CheckWatchListUseCase {
    override fun invoke(id: Long, type: ContentType): Flow<Result<Boolean>> {
        return repository.checkWatchList(id, type).flowOn(Dispatchers.IO)
    }
}