package id.my.arieftb.meowvie.domain.usecase.movies

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content

interface GetMoviesUseCase {
    suspend operator fun invoke(
        page: Int = 1,
        sortBy: String? = "release_date.desc",
        releaseDateLte: String? = null,
        releaseDateGte: String? = null
    ): Result<List<Content>>
}