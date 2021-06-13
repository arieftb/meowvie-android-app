package id.my.arieftb.meowvie.domain.usecase.movies.highlight

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content

interface GetMoviesHighlightUseCase {
    suspend fun invoke(limit: Int = 6): Result<List<Content>>
}