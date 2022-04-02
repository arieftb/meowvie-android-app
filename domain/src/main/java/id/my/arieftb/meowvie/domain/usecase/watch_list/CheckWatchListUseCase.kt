package id.my.arieftb.meowvie.domain.usecase.watch_list

import id.my.arieftb.meowvie.domain.constant.ContentType
import id.my.arieftb.meowvie.domain.model.entity.Result
import kotlinx.coroutines.flow.Flow

interface CheckWatchListUseCase {
    fun invoke(id: Long, type: ContentType): Flow<Result<Boolean>>
}