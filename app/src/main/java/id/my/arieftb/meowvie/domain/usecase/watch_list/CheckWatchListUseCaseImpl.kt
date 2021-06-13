package id.my.arieftb.meowvie.domain.usecase.watch_list

import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.repo.WatchListRepository
import javax.inject.Inject

class CheckWatchListUseCaseImpl @Inject constructor(
    private val repository: WatchListRepository
) : CheckWatchListUseCase {
    override suspend fun invoke(id: Long, type: ContentType): Result<Boolean> {
        return repository.checkWatchList(id, type)
    }
}