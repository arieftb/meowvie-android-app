package id.my.arieftb.meowvie.domain.usecase.movies.upcoming

import id.my.arieftb.meowvie.domain.model.entity.Result
import id.my.arieftb.meowvie.domain.model.entity.base.Content
import kotlinx.coroutines.flow.Flow

interface GetMoviesUpcomingHighlightUseCase {
    fun invoke(limit: Int = 6): Flow<Result<List<Content>>>
}