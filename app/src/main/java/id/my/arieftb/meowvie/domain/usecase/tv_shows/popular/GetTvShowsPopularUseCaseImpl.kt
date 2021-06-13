package id.my.arieftb.meowvie.domain.usecase.tv_shows.popular

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.usecase.date.GetCurrentDateUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.GetTvShowsUseCase
import javax.inject.Inject

class GetTvShowsPopularUseCaseImpl @Inject constructor(
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val getTvShowsUseCase: GetTvShowsUseCase
) : GetTvShowsPopularUseCase {
    override suspend fun invoke(page: Int): Result<List<Content>> {
        return getTvShowsUseCase.invoke(
            page = page,
            releaseDateLte = getCurrentDateUseCase.invoke("yyyy-MM-dd"),
            sortBy = "popularity.desc"
        )
    }
}