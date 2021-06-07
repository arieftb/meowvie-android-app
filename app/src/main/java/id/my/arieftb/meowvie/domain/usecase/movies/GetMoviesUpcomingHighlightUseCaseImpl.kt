package id.my.arieftb.meowvie.domain.usecase.movies

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.movie.Movie
import javax.inject.Inject

class GetMoviesUpcomingHighlightUseCaseImpl @Inject constructor(private val getMoviesUpcomingUseCase: GetMoviesUpcomingUseCase) :
    GetMoviesUpcomingHighlightUseCase {
    override suspend fun invoke(limit: Int): Result<List<Movie>> {
        return when (val result = getMoviesUpcomingUseCase.invoke()) {
            is Result.Failure -> result
            is Result.Success -> {
                return Result.Success(data = result.data.take(limit))
            }
        }
    }
}