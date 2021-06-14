package id.my.arieftb.meowvie.domain.usecase.movies.upcoming

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content

interface GetMoviesUpcomingUseCase {
    suspend fun invoke(page: Int = 1): Result<List<Content>>
}