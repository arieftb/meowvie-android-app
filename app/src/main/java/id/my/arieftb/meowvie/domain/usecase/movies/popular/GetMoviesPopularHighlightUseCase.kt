package id.my.arieftb.meowvie.domain.usecase.movies.popular

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import kotlinx.coroutines.flow.Flow

interface GetMoviesPopularHighlightUseCase {
    fun invoke(limit: Int = 6): Flow<Result<List<Content>>>
}