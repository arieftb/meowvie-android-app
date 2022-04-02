package id.my.arieftb.meowvie.domain.usecase.tv_shows.detail

import id.my.arieftb.meowvie.domain.model.entity.Result
import id.my.arieftb.meowvie.domain.model.entity.base.ContentDetail
import kotlinx.coroutines.flow.Flow

interface GetTvShowDetailUseCase {
    fun invoke(id: Long): Flow<Result<ContentDetail>>
}