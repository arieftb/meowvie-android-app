package id.my.arieftb.meowvie.domain.usecase.tv_shows.upcoming

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.usecase.date.GetDateDayAheadUseCase
import id.my.arieftb.meowvie.domain.usecase.date.GetDateMonthAheadUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.GetTvShowsUseCase
import javax.inject.Inject

class GetTvShowsUpcomingUseCaseImpl @Inject constructor(
    private val getDateMonthAheadUseCase: GetDateMonthAheadUseCase,
    private val getDateDayAheadUseCase: GetDateDayAheadUseCase,
    private val getTvShowsUseCase: GetTvShowsUseCase
) : GetTvShowsUpcomingUseCase {
    override suspend fun invoke(page: Int): Result<List<Content>> {
        return getTvShowsUseCase.invoke(
            page = page,
            releaseDateGte = getDateDayAheadUseCase.invoke("yyyy-MM-dd", 1),
            releaseDateLte = getDateMonthAheadUseCase.invoke("yyyy-MM-dd", 1),
            sortBy = "first_air_date.asc"
        )
    }
}