package id.my.arieftb.meowvie.domain.usecase.tv_shows.detail

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.ContentDetail

interface GetTvShowDetailUseCase {
    suspend fun invoke(id: Long): Result<ContentDetail>
}