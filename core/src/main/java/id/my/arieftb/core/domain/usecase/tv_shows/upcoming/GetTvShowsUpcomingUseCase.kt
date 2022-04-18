package id.my.arieftb.core.domain.usecase.tv_shows.upcoming

import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.Content
import kotlinx.coroutines.flow.Flow

interface GetTvShowsUpcomingUseCase {
    fun invoke(page: Int = 1): Flow<ResultEntity<List<Content>>>
}