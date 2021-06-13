package id.my.arieftb.meowvie.domain.usecase.tv_shows

import id.my.arieftb.meowvie.data.model.request.content.ContentSaveRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.repo.TvShowRepository
import id.my.arieftb.meowvie.domain.usecase.date.GetCurrentDateTimeMillisUseCase
import javax.inject.Inject

class SaveTvShowWatchListUseCaseImpl @Inject constructor(
    private val repository: TvShowRepository,
    private val getCurrentDateTimeMillisUseCase: GetCurrentDateTimeMillisUseCase
): SaveTvShowWatchListUseCase {
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