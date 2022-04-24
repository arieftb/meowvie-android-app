package id.my.arieftb.core.domain.usecase.tv_shows.detail

import id.my.arieftb.core.data.model.request.detail.DetailRequest
import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.ContentDetail
import id.my.arieftb.core.domain.repo.TvShowRepository
import id.my.arieftb.core.domain.usecase.language.GetLanguageUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

class GetTvShowDetailUseCaseImpl @Inject constructor(
    private val getLanguageUseCase: GetLanguageUseCase,
    private val repository: TvShowRepository
) : GetTvShowDetailUseCase {
    override fun invoke(id: Long): Flow<ResultEntity<ContentDetail>> {
        return getLanguageUseCase.invoke().flatMapConcat { lang ->
            val request = DetailRequest().apply {
                this.id = id
                this.language = lang
            }

            repository.fetch(request)
        }
    }
}