package id.my.arieftb.meowvie.domain.usecase.tv_shows.popular

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import javax.inject.Inject

class GetTvShowsPopularHighlightUseCaseImpl @Inject constructor(
    private val getTvShowsPopularUseCase: GetTvShowsPopularUseCase
) : GetTvShowsPopularHighlightUseCase {
    override suspend fun invoke(limit: Int): Result<List<Content>> {
        return when (val result = getTvShowsPopularUseCase.invoke()) {
            is Result.Success -> Result.Success(data = result.data.take(limit))
            else -> result
        }
    }
}