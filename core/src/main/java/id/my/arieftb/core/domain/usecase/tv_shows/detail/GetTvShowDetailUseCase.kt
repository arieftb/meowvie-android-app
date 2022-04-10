package id.my.arieftb.core.domain.usecase.tv_shows.detail

import id.my.arieftb.core.domain.model.Result
import id.my.arieftb.core.domain.model.base.ContentDetail
import kotlinx.coroutines.flow.Flow

interface GetTvShowDetailUseCase {
    fun invoke(id: Long): Flow<Result<ContentDetail>>
}