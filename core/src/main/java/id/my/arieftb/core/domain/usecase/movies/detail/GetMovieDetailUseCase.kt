package id.my.arieftb.core.domain.usecase.movies.detail

import id.my.arieftb.core.domain.model.Result
import id.my.arieftb.core.domain.model.base.ContentDetail
import kotlinx.coroutines.flow.Flow

interface GetMovieDetailUseCase {
    fun invoke(id: Long): Flow<Result<ContentDetail>>
}