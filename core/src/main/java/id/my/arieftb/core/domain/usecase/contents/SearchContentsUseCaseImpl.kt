package id.my.arieftb.core.domain.usecase.contents

import id.my.arieftb.core.data.model.request.content.ContentSearchRequest
import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.Content
import id.my.arieftb.core.domain.repo.ContentRepository
import id.my.arieftb.core.domain.usecase.language.GetLanguageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchContentsUseCaseImpl @Inject constructor(
    private val repository: ContentRepository,
    private val getLanguageUseCase: GetLanguageUseCase
) : SearchContentsUseCase {

    @OptIn(FlowPreview::class)
    override fun invoke(page: Int, keyword: String): Flow<ResultEntity<List<Content>>> {
        return getLanguageUseCase.invoke().flatMapMerge { language ->
            repository.search(
                ContentSearchRequest(
                    keyword,
                    page,
                    language
                )
            )
        }.flowOn(Dispatchers.IO)
    }
}