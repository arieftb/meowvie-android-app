package id.my.arieftb.core.domain.usecase.movies.popular

import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.Content
import kotlinx.coroutines.flow.Flow

interface GetMoviesPopularUseCase {
    fun invoke(page: Int = 1): Flow<ResultEntity<List<Content>>>
}