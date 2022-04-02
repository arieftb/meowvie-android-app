package id.my.arieftb.meowvie.domain.usecase.watch_list

import id.my.arieftb.meowvie.domain.model.entity.Result
import id.my.arieftb.meowvie.domain.model.entity.base.Content
import kotlinx.coroutines.flow.Flow

interface SaveWatchListUseCase {
    fun invoke(content: Content): Flow<Result<Boolean>>
}