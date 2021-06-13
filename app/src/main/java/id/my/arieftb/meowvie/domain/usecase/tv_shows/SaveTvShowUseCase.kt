package id.my.arieftb.meowvie.domain.usecase.tv_shows

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content

interface SaveTvShowUseCase {
    suspend fun invoke(content: Content): Result<Boolean>
}