package id.my.arieftb.meowvie.domain.usecase.tv_shows.popular

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content

interface GetTvShowsPopularUseCase {
    suspend fun invoke(page: Int = 1): Result<List<Content>>
}