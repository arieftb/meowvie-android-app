package id.my.arieftb.meowvie.domain.usecase.tv_shows

import id.my.arieftb.meowvie.domain.model.Result

interface CheckTvShowUseCase {
    suspend fun invoke(code: Long): Result<Boolean>
}