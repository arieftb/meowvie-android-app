package id.my.arieftb.core.domain.usecase.movies

import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.Content
import kotlinx.coroutines.flow.Flow

interface GetMoviesUseCase {
    operator fun invoke(
        page: Int = 1,
        sortBy: String? = "release_date.desc",
        releaseDateLte: String? = null,
        releaseDateGte: String? = null
    ): Flow<ResultEntity<List<Content>>>
}