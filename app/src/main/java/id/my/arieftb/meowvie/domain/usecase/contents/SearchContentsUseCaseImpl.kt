package id.my.arieftb.meowvie.domain.usecase.contents

import id.my.arieftb.meowvie.data.model.request.content.ContentSearchRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.repo.ContentRepository
import id.my.arieftb.meowvie.domain.usecase.language.GetLanguageUseCase
import javax.inject.Inject

class SearchContentsUseCaseImpl @Inject constructor(
    private val repository: ContentRepository,
    private val getLanguageUseCase: GetLanguageUseCase
) : SearchContentsUseCase {

    override suspend fun invoke(page: Int, keyword: String): Result<List<Content>> {
        return repository.search(
            ContentSearchRequest(
                keyword,
                page,
                getLanguageUseCase.invoke()
            ),
            Content()
        )
    }
}