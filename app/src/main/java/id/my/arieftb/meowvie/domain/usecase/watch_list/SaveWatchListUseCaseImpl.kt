package id.my.arieftb.meowvie.domain.usecase.watch_list

import id.my.arieftb.meowvie.data.model.request.content.ContentSaveRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.repo.WatchListRepository
import id.my.arieftb.meowvie.domain.usecase.date.GetCurrentDateTimeMillisUseCase

class SaveWatchListUseCaseImpl(
    private val getCurrentDateTimeMillisUseCase: GetCurrentDateTimeMillisUseCase,
    private val repository: WatchListRepository
): SaveWatchListUseCase {
    override suspend fun invoke(content: Content): Result<Boolean> {
        val request = ContentSaveRequest(
            content.id,
            content.title,
            content.posterPath,
            content.bannerPath,
            content.type,
            createdAt = getCurrentDateTimeMillisUseCase.invoke()
        )
        return repository.saveWatchList(request)
    }
}