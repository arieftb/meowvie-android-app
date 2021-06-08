package id.my.arieftb.meowvie.domain.usecase.tv_shows

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.tv_show.TvShow

class GetTvShowsPopularHighlightUseCaseImpl constructor(
    private val getTvShowsPopularUseCase: GetTvShowsPopularUseCase
) : GetTvShowsPopularHighlightUseCase {
    override suspend fun invoke(limit: Int): Result<List<TvShow>> {
        return when (val result = getTvShowsPopularUseCase.invoke()) {
            is Result.Success -> Result.Success(data = result.data.take(limit))
            else -> result
        }
    }
}