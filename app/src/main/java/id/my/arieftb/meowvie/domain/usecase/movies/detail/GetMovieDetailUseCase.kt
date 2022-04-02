package id.my.arieftb.meowvie.domain.usecase.movies.detail

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.ContentDetail
import kotlinx.coroutines.flow.Flow

interface GetMovieDetailUseCase {
    fun invoke(id: Long): Flow<Result<ContentDetail>>
}