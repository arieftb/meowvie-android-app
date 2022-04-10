package id.my.arieftb.core.domain.usecase.contents

import id.my.arieftb.core.domain.model.Result
import id.my.arieftb.core.domain.model.base.Content
import kotlinx.coroutines.flow.Flow

interface SearchContentsUseCase {
    fun invoke(page: Int = 1, keyword: String) : Flow<Result<List<Content>>>
}