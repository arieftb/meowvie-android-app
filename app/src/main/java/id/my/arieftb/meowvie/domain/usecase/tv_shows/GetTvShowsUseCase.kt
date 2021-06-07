package id.my.arieftb.meowvie.domain.usecase.tv_shows

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.tv_show.TvShow

interface GetTvShowsUseCase {
    suspend fun invoke(
        page: Int = 1,
        sortBy: String? = "release_date.desc",
        releaseDateLte: String? = null,
        releaseDateGte: String? = null
    ): Result<List<TvShow>>
}