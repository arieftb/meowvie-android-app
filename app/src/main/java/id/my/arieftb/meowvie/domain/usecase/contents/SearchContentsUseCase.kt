package id.my.arieftb.meowvie.domain.usecase.contents

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import kotlinx.coroutines.flow.Flow

interface SearchContentsUseCase {
    fun invoke(page: Int = 1, keyword: String) : Flow<Result<List<Content>>>
}