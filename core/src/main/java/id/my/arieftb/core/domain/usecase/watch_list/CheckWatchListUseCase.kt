package id.my.arieftb.core.domain.usecase.watch_list

import id.my.arieftb.core.domain.constant.ContentType
import id.my.arieftb.core.domain.model.ResultEntity
import kotlinx.coroutines.flow.Flow

interface CheckWatchListUseCase {
    fun invoke(id: Long, type: ContentType): Flow<ResultEntity<Boolean>>
}