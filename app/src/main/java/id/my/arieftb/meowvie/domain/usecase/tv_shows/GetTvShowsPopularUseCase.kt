package id.my.arieftb.meowvie.domain.usecase.tv_shows

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.tv_show.TvShow

interface GetTvShowsPopularUseCase {
    suspend fun invoke(page: Int = 1): Result<List<TvShow>>
}