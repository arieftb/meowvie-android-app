package id.my.arieftb.core.domain.usecase.movies.popular

import id.my.arieftb.core.domain.model.Result
import id.my.arieftb.core.domain.model.base.Content
import kotlinx.coroutines.flow.Flow

interface GetMoviesPopularUseCase {
    fun invoke(page: Int = 1): Flow<Result<List<Content>>>
}