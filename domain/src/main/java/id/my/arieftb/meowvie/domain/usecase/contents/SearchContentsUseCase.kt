package id.my.arieftb.meowvie.domain.usecase.contents

import id.my.arieftb.meowvie.domain.model.entity.Result
import id.my.arieftb.meowvie.domain.model.entity.base.Content
import kotlinx.coroutines.flow.Flow

interface SearchContentsUseCase {
    fun invoke(page: Int = 1, keyword: String) : Flow<Result<List<Content>>>
}