package id.my.arieftb.core.domain.usecase.watch_list

import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.Content
import kotlinx.coroutines.flow.Flow

interface SaveWatchListUseCase {
    fun invoke(content: Content): Flow<ResultEntity<Boolean>>
}