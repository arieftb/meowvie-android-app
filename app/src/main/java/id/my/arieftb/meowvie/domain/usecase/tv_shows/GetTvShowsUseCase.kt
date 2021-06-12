package id.my.arieftb.meowvie.domain.usecase.tv_shows

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content

interface GetTvShowsUseCase {
    suspend fun invoke(
        page: Int = 1,
        sortBy: String? = "first_air_date.desc",
        releaseDateLte: String? = null,
        releaseDateGte: String? = null
    ): Result<List<Content>>
}