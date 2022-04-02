package id.my.arieftb.meowvie.domain.usecase.tv_shows.upcoming

import id.my.arieftb.meowvie.domain.model.entity.Result
import id.my.arieftb.meowvie.domain.model.entity.base.Content
import kotlinx.coroutines.flow.Flow

interface GetTvShowsUpcomingUseCase {
    fun invoke(page: Int = 1): Flow<Result<List<Content>>>
}