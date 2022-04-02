package id.my.arieftb.meowvie.domain.usecase.movies.popular

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMoviesPopularHighlightUseCaseImpl @Inject constructor(
    private val getMoviesPopularUseCase: GetMoviesPopularUseCase
) : GetMoviesPopularHighlightUseCase {
    override fun invoke(limit: Int): Flow<Result<List<Content>>> {
        return getMoviesPopularUseCase.invoke().map { result ->
            when (result) {
                is Result.Failure -> result
                is Result.Success -> Result.Success(data = result.data.take(limit))
            }
        }
    }
}