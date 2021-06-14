package id.my.arieftb.meowvie.domain.usecase.tv_shows.upcoming

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content

interface GetTvShowsUpcomingHighlightUseCase {
    suspend fun invoke(limit: Int = 6): Result<List<Content>>
}