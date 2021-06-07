package id.my.arieftb.meowvie.domain.usecase.movies

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.movie.Movie
import id.my.arieftb.meowvie.domain.usecase.date.GetCurrentDateUseCase
import id.my.arieftb.meowvie.domain.usecase.date.GetDateMonthAheadUseCase
import javax.inject.Inject

class GetMoviesUpcomingUseCaseImpl @Inject constructor(
    private val getDateMonthAheadUseCase: GetDateMonthAheadUseCase,
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val getMoviesUseCase: GetMoviesUseCase
) : GetMoviesUpcomingUseCase {
    override suspend fun invoke(): Result<List<Movie>> {
        return getMoviesUseCase.invoke(
            releaseDateGte = getCurrentDateUseCase.invoke("yyyy-MM-dd"),
            releaseDateLte = getDateMonthAheadUseCase.invoke("yyyy-MM-dd", 1),
            sortBy = "release_date.asc"
        )
    }
}