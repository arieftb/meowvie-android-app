package id.my.arieftb.meowvie.domain.usecase.movies.popular

import id.my.arieftb.meowvie.domain.model.entity.Result
import id.my.arieftb.meowvie.domain.model.entity.base.Content
import kotlinx.coroutines.flow.Flow

interface GetMoviesPopularUseCase {
    fun invoke(page: Int = 1): Flow<Result<List<Content>>>
}