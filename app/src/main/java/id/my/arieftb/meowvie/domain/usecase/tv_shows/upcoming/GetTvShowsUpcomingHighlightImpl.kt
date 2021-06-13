package id.my.arieftb.meowvie.domain.usecase.tv_shows.upcoming

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import javax.inject.Inject

class GetTvShowsUpcomingHighlightImpl @Inject constructor(
    private val getTvShowsUpcomingUseCaseUseCase: GetTvShowsUpcomingUseCase
) : GetTvShowsUpcomingHighlightUseCase {
    override suspend fun invoke(limit: Int): Result<List<Content>> {
        return when (val result = getTvShowsUpcomingUseCaseUseCase.invoke()) {
            is Result.Failure -> result
            is Result.Success -> return Result.Success(data = result.data.take(limit))
        }
    }
}