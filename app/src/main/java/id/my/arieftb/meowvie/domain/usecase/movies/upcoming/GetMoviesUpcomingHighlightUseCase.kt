package id.my.arieftb.meowvie.domain.usecase.movies.upcoming

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content

interface GetMoviesUpcomingHighlightUseCase {
    suspend fun invoke(limit: Int = 6): Result<List<Content>>
}