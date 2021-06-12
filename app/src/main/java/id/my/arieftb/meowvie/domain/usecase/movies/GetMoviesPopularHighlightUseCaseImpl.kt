package id.my.arieftb.meowvie.domain.usecase.movies

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import javax.inject.Inject

class GetMoviesPopularHighlightUseCaseImpl @Inject constructor(
    private val getMoviesPopularUseCase: GetMoviesPopularUseCase
) : GetMoviesPopularHighlightUseCase {
    override suspend fun invoke(limit: Int): Result<List<Content>> {
        return when (val result = getMoviesPopularUseCase.invoke()) {
            is Result.Success -> {
                Result.Success(data = result.data.take(limit))
            }
            else -> result
        }
    }
}