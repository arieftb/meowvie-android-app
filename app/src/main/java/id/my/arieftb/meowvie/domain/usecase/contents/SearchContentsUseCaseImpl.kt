package id.my.arieftb.meowvie.domain.usecase.contents

import id.my.arieftb.meowvie.data.model.request.content.ContentSearchRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.repo.ContentRepository
import id.my.arieftb.meowvie.domain.usecase.language.GetLanguageUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchContentsUseCaseImpl @Inject constructor(
    private val repository: ContentRepository,
    private val getLanguageUseCase: GetLanguageUseCase
) : SearchContentsUseCase {

    override fun invoke(page: Int, keyword: String): Flow<Result<List<Content>>> {
        return getLanguageUseCase.invoke().flatMapMerge { language ->
            repository.search(
                ContentSearchRequest(
                    keyword,
                    page,
                    language
                )
            )
        }
    }
}