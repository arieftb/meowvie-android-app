package id.my.arieftb.meowvie.domain.usecase.tv_shows.upcoming

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content

interface GetTvShowsUpcomingUseCase {
    suspend fun invoke(page: Int = 1): Result<List<Content>>
}