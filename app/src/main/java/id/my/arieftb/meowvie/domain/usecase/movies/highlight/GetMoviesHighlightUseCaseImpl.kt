package id.my.arieftb.meowvie.domain.usecase.movies.highlight

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.usecase.movies.GetMoviesUseCase
import javax.inject.Inject

class GetMoviesHighlightUseCaseImpl @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase) :
    GetMoviesHighlightUseCase {
    override suspend fun invoke(limit: Int): Result<List<Content>> {
        return when (val result = getMoviesUseCase.invoke()) {
            is Result.Failure -> result
            is Result.Success -> {
                return Result.Success(data = result.data.take(limit))
            }
        }
    }
}