package id.my.arieftb.meowvie.domain.usecase.tv_shows

import id.my.arieftb.meowvie.data.model.request.detail.DetailRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.tv_show.TvShowDetail
import id.my.arieftb.meowvie.domain.repo.TvShowRepository
import id.my.arieftb.meowvie.domain.usecase.language.GetLanguageUseCase

class GetTvShowDetailUseCaseImpl constructor(
    private val getLanguageUseCase: GetLanguageUseCase,
    private val repository: TvShowRepository
) : GetTvShowDetailUseCase {
    override suspend fun invoke(id: Long): Result<TvShowDetail> {
        val request = DetailRequest().apply {
            this.id = id
            this.language = getLanguageUseCase.invoke()
        }
        val data = TvShowDetail()
        return repository.fetch(request, data)
    }
}