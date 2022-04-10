package id.my.arieftb.core.domain.usecase.watch_list

import id.my.arieftb.core.domain.constant.ContentType
import id.my.arieftb.core.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface RemoveWatchListUseCase {
    fun invoke(id: Long, type: ContentType): Flow<Result<Boolean>>
}