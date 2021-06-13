package id.my.arieftb.meowvie.domain.usecase.movies

import id.my.arieftb.meowvie.data.model.request.content.ContentSaveRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.repo.MovieRepository
import id.my.arieftb.meowvie.domain.usecase.date.GetCurrentDateTimeMillisUseCase
import javax.inject.Inject

class SaveMovieWatchListUseCaseImpl @Inject constructor(
    private val repository: MovieRepository,
    private val getCurrentDateTimeMillisUseCase: GetCurrentDateTimeMillisUseCase
) : SaveMovieWatchListUseCase {

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