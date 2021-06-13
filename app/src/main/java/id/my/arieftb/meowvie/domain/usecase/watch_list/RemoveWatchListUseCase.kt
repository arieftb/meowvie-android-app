package id.my.arieftb.meowvie.domain.usecase.watch_list

import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.domain.model.Result

interface RemoveWatchListUseCase {
    suspend fun invoke(id: Long, type: ContentType): Result<Boolean>
}