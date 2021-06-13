package id.my.arieftb.meowvie.domain.usecase.tv_shows

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content

interface SaveTvShowWatchListUseCase {
    suspend fun invoke(content: Content): Result<Boolean>
}