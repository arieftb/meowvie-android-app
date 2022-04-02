package id.my.arieftb.meowvie.domain.usecase.contents

import id.my.arieftb.meowvie.domain.model.entity.Result
import id.my.arieftb.meowvie.domain.model.entity.base.Content
import id.my.arieftb.meowvie.domain.model.request.content.ContentSearchRequest
import id.my.arieftb.meowvie.domain.repo.ContentRepository
import id.my.arieftb.meowvie.domain.usecase.language.GetLanguageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOn
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
        }.flowOn(Dispatchers.IO)
    }
}