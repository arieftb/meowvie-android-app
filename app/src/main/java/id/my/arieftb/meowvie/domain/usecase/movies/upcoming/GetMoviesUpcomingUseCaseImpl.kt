package id.my.arieftb.meowvie.domain.usecase.movies.upcoming

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.usecase.date.GetDateDayAheadUseCase
import id.my.arieftb.meowvie.domain.usecase.date.GetDateMonthAheadUseCase
import id.my.arieftb.meowvie.domain.usecase.movies.GetMoviesUseCase
import javax.inject.Inject

class GetMoviesUpcomingUseCaseImpl @Inject constructor(
    private val getDateMonthAheadUseCase: GetDateMonthAheadUseCase,
    private val getDateDayAheadUseCase: GetDateDayAheadUseCase,
    private val getMoviesUseCase: GetMoviesUseCase
) : GetMoviesUpcomingUseCase {
    override suspend fun invoke(): Result<List<Content>> {
        return getMoviesUseCase.invoke(
            releaseDateGte = getDateDayAheadUseCase.invoke("yyyy-MM-dd", 1),
            releaseDateLte = getDateMonthAheadUseCase.invoke("yyyy-MM-dd", 1),
            sortBy = "release_date.asc"
        )
    }
}