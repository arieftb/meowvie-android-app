package id.my.arieftb.meowvie.domain.usecase.tv_shows.popular

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import kotlinx.coroutines.flow.Flow

interface GetTvShowsPopularUseCase {
    fun invoke(page: Int = 1): Flow<Result<List<Content>>>
}