package id.my.arieftb.meowvie.domain.usecase.tv_shows.popular

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content

interface GetTvShowsPopularHighlightUseCase {
    suspend fun invoke(limit: Int = 6): Result<List<Content>>
}