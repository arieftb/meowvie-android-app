package id.my.arieftb.meowvie.domain.usecase.tv_shows

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.tv_show.TvShowDetail

interface GetTvShowDetailUseCase {
    suspend fun invoke(id: Long): Result<TvShowDetail>
}