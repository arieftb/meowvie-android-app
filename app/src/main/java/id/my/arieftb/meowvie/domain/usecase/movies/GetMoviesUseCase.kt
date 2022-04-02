package id.my.arieftb.meowvie.domain.usecase.movies

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import kotlinx.coroutines.flow.Flow

interface GetMoviesUseCase {
    operator fun invoke(
        page: Int = 1,
        sortBy: String? = "release_date.desc",
        releaseDateLte: String? = null,
        releaseDateGte: String? = null
    ): Flow<Result<List<Content>>>
}