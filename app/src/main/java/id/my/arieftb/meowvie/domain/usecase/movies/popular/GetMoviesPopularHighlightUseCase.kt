package id.my.arieftb.meowvie.domain.usecase.movies.popular

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content

interface GetMoviesPopularHighlightUseCase {
    suspend fun invoke(limit: Int = 6): Result<List<Content>>
}