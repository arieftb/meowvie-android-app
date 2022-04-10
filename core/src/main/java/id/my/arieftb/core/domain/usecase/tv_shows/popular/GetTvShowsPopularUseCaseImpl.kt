package id.my.arieftb.core.domain.usecase.tv_shows.popular

import id.my.arieftb.core.domain.model.Result
import id.my.arieftb.core.domain.model.base.Content
import id.my.arieftb.core.domain.usecase.date.GetCurrentDateUseCase
import id.my.arieftb.core.domain.usecase.tv_shows.GetTvShowsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

class GetTvShowsPopularUseCaseImpl @Inject constructor(
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val getTvShowsUseCase: GetTvShowsUseCase
) : GetTvShowsPopularUseCase {
    override fun invoke(page: Int): Flow<Result<List<Content>>> {
        return getCurrentDateUseCase.invoke("yyyy-MM-dd").flatMapConcat { date ->
            getTvShowsUseCase.invoke(
                page = page,
                releaseDateLte = date,
                sortBy = "popularity.desc"
            )
        }
    }
}