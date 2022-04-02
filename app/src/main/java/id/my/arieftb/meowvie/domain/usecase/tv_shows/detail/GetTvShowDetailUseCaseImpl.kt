package id.my.arieftb.meowvie.domain.usecase.tv_shows.detail

import id.my.arieftb.meowvie.data.model.request.detail.DetailRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.ContentDetail
import id.my.arieftb.meowvie.domain.model.tv_show.TvShowDetail
import id.my.arieftb.meowvie.domain.repo.TvShowRepository
import id.my.arieftb.meowvie.domain.usecase.language.GetLanguageUseCase
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