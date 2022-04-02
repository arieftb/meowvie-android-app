package id.my.arieftb.meowvie.domain.usecase.movies.upcoming

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.usecase.date.GetDateDayAheadUseCase
import id.my.arieftb.meowvie.domain.usecase.date.GetDateMonthAheadUseCase
import id.my.arieftb.meowvie.domain.usecase.movies.GetMoviesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class GetMoviesUpcomingUseCaseImpl @Inject constructor(
    private val getDateMonthAheadUseCase: GetDateMonthAheadUseCase,
    private val getDateDayAheadUseCase: GetDateDayAheadUseCase,
    private val getMoviesUseCase: GetMoviesUseCase
) : GetMoviesUpcomingUseCase {
    override fun invoke(page: Int): Flow<Result<List<Content>>> {
        return getDateMonthAheadUseCase.invoke("yyyy-MM-dd", 1)
            .zip(getDateDayAheadUseCase.invoke("yyyy-MM-dd", 1)) { month, date ->
                getMoviesUseCase.invoke(
                    page = page,
                    releaseDateGte = date,
                    releaseDateLte = month,
                    sortBy = "release_date.asc"
                )
            }.flatMapConcat {
            it
        }
    }
}