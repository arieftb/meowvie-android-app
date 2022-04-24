package id.my.arieftb.core.domain.usecase.tv_shows.upcoming

import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.Content
import id.my.arieftb.core.domain.usecase.date.GetDateDayAheadUseCase
import id.my.arieftb.core.domain.usecase.date.GetDateMonthAheadUseCase
import id.my.arieftb.core.domain.usecase.tv_shows.GetTvShowsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetTvShowsUpcomingUseCaseImpl @Inject constructor(
    private val getDateMonthAheadUseCase: GetDateMonthAheadUseCase,
    private val getDateDayAheadUseCase: GetDateDayAheadUseCase,
    private val getTvShowsUseCase: GetTvShowsUseCase
) : GetTvShowsUpcomingUseCase {
    override fun invoke(page: Int): Flow<ResultEntity<List<Content>>> {
        return getDateDayAheadUseCase.invoke("yyyy-MM-dd", 1).flatMapConcat { date ->
            getDateMonthAheadUseCase.invoke("yyyy-MM-dd", 1).flatMapConcat { month ->
                getTvShowsUseCase.invoke(
                    page = page,
                    releaseDateGte = date,
                    releaseDateLte = month,
                    sortBy = "release_date.asc"
                )
            }
        }.flowOn(Dispatchers.IO)
    }
}