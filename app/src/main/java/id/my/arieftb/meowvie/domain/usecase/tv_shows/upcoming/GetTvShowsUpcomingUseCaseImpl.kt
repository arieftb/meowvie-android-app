package id.my.arieftb.meowvie.domain.usecase.tv_shows.upcoming

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.usecase.date.GetDateDayAheadUseCase
import id.my.arieftb.meowvie.domain.usecase.date.GetDateMonthAheadUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.GetTvShowsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class GetTvShowsUpcomingUseCaseImpl @Inject constructor(
    private val getDateMonthAheadUseCase: GetDateMonthAheadUseCase,
    private val getDateDayAheadUseCase: GetDateDayAheadUseCase,
    private val getTvShowsUseCase: GetTvShowsUseCase
) : GetTvShowsUpcomingUseCase {
    override fun invoke(page: Int): Flow<Result<List<Content>>> {
        return getDateDayAheadUseCase.invoke("yyyy-MM-dd", 1)
            .zip(getDateMonthAheadUseCase.invoke("yyyy-MM-dd", 1)) { date, month ->
                getTvShowsUseCase.invoke(
                    page = page,
                    releaseDateGte = date,
                    releaseDateLte = month,
                    sortBy = "first_air_date.asc"
                )
            }.flatMapConcat { result ->
                result
            }.flowOn(Dispatchers.IO)
    }
}