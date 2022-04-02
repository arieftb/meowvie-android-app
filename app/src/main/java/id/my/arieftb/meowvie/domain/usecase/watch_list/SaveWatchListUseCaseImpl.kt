package id.my.arieftb.meowvie.domain.usecase.watch_list

import id.my.arieftb.meowvie.data.model.request.content.ContentSaveRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.repo.WatchListRepository
import id.my.arieftb.meowvie.domain.usecase.date.GetCurrentDateTimeMillisUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn

class SaveWatchListUseCaseImpl(
    private val getCurrentDateTimeMillisUseCase: GetCurrentDateTimeMillisUseCase,
    private val repository: WatchListRepository
) : SaveWatchListUseCase {
    override fun invoke(content: Content): Flow<Result<Boolean>> {
        return getCurrentDateTimeMillisUseCase.invoke().flatMapConcat { time ->
            val request = ContentSaveRequest(
                content.id,
                content.title,
                content.posterPath,
                content.bannerPath,
                content.type,
                createdAt = time
            )
            repository.saveWatchList(request)
        }.flowOn(Dispatchers.IO)
    }
}