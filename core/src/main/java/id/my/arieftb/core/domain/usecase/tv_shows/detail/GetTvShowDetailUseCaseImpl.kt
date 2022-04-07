package id.my.arieftb.core.domain.usecase.tv_shows.detail

import id.my.arieftb.core.data.model.request.detail.DetailRequest
import id.my.arieftb.core.domain.model.Result
import id.my.arieftb.core.domain.model.base.ContentDetail
import id.my.arieftb.core.domain.repo.TvShowRepository
import id.my.arieftb.core.domain.usecase.language.GetLanguageUseCase
import javax.inject.Inject

class GetTvShowDetailUseCaseImpl @Inject constructor(
    private val getLanguageUseCase: GetLanguageUseCase,
    private val repository: TvShowRepository
) : GetTvShowDetailUseCase {
    override suspend fun invoke(id: Long): Result<ContentDetail> {
        val request = DetailRequest().apply {
            this.id = id
            this.language = "en"
        }
        return repository.fetch(request)
    }
}