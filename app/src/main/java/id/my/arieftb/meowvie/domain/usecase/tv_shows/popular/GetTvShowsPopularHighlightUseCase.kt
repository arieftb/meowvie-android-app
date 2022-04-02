package id.my.arieftb.meowvie.domain.usecase.tv_shows.popular

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import kotlinx.coroutines.flow.Flow

interface GetTvShowsPopularHighlightUseCase {
    fun invoke(limit: Int = 6): Flow<Result<List<Content>>>
}