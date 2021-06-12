package id.my.arieftb.meowvie.domain.usecase.movies

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content

interface GetMoviesPopularUseCase {
    suspend fun invoke(page: Int = 1): Result<List<Content>>
}