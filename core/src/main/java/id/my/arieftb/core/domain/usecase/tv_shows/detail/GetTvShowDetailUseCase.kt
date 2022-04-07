package id.my.arieftb.core.domain.usecase.tv_shows.detail

import id.my.arieftb.core.domain.model.Result
import id.my.arieftb.core.domain.model.base.ContentDetail

interface GetTvShowDetailUseCase {
    suspend fun invoke(id: Long): Result<ContentDetail>
}