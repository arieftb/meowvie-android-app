package id.my.arieftb.core.domain.usecase.tv_shows.popular

import id.my.arieftb.core.domain.model.Result
import id.my.arieftb.core.domain.model.base.Content
import kotlinx.coroutines.flow.Flow

interface GetTvShowsPopularUseCase {
    fun invoke(page: Int = 1): Flow<Result<List<Content>>>
}