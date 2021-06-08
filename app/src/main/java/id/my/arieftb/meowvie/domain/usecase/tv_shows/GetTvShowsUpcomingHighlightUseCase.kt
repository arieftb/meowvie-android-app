package id.my.arieftb.meowvie.domain.usecase.tv_shows

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.tv_show.TvShow

interface GetTvShowsUpcomingHighlightUseCase {
    suspend fun invoke(limit: Int = 6): Result<List<TvShow>>
}