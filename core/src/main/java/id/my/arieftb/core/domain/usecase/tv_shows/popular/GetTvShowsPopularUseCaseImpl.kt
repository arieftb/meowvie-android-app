package id.my.arieftb.core.domain.usecase.tv_shows.popular

import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.Content
import id.my.arieftb.core.domain.usecase.date.GetCurrentDateUseCase
import id.my.arieftb.core.domain.usecase.tv_shows.GetTvShowsUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

class GetTvShowsPopularUseCaseImpl @Inject constructor(
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val getTvShowsUseCase: GetTvShowsUseCase
) : GetTvShowsPopularUseCase {
    @OptIn(FlowPreview::class)
    override fun invoke(page: Int): Flow<ResultEntity<List<Content>>> {
        return getCurrentDateUseCase.invoke("yyyy-MM-dd").flatMapConcat { date ->
            getTvShowsUseCase.invoke(
                page = page,
                releaseDateLte = date,
                sortBy = "popularity.desc"
            )
        }
    }
}