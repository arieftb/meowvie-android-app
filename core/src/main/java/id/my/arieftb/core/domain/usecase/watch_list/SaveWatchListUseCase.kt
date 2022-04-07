package id.my.arieftb.core.domain.usecase.watch_list

import id.my.arieftb.core.domain.model.Result
import id.my.arieftb.core.domain.model.base.Content
import kotlinx.coroutines.flow.Flow

interface SaveWatchListUseCase {
    fun invoke(content: Content): Flow<Result<Boolean>>
}