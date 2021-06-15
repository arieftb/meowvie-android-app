package id.my.arieftb.meowvie.domain.usecase.contents

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content

interface SearchContentsUseCase {
    suspend fun invoke(page: Int = 1, keyword: String) : Result<List<Content>>
}