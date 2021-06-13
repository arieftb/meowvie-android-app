package id.my.arieftb.meowvie.domain.usecase.watch_list

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content

interface SaveWatchListUseCase {
    suspend fun invoke(content: Content): Result<Boolean>
}