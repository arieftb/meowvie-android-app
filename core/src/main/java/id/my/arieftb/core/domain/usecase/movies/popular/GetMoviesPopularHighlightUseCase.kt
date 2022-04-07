package id.my.arieftb.core.domain.usecase.movies.popular

import id.my.arieftb.core.domain.model.Result
import id.my.arieftb.core.domain.model.base.Content
import kotlinx.coroutines.flow.Flow

interface GetMoviesPopularHighlightUseCase {
    fun invoke(limit: Int = 6): Flow<Result<List<Content>>>
}