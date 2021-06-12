package id.my.arieftb.meowvie.domain.usecase.movies

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.ContentDetail

interface GetMovieDetailUseCase {
    suspend fun invoke(id: Long): Result<ContentDetail>
}