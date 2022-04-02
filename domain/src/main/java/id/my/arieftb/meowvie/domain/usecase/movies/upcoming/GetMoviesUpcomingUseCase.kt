package id.my.arieftb.meowvie.domain.usecase.movies.upcoming

import id.my.arieftb.meowvie.domain.model.entity.Result
import id.my.arieftb.meowvie.domain.model.entity.base.Content
import kotlinx.coroutines.flow.Flow

interface GetMoviesUpcomingUseCase {
    fun invoke(page: Int = 1): Flow<Result<List<Content>>>
}