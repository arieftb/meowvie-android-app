package id.my.arieftb.meowvie.domain.usecase.tv_shows

import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.tv_show.TvShow
import id.my.arieftb.meowvie.domain.repo.TvShowRepository
import id.my.arieftb.meowvie.domain.usecase.date.GetCurrentDateUseCase
import id.my.arieftb.meowvie.domain.usecase.language.GetLanguageUseCase

class GetTvShowsUseCaseImpl constructor(
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val repository: TvShowRepository
) : GetTvShowsUseCase {
    override suspend fun invoke(
        page: Int,
        sortBy: String?,
        releaseDateLte: String?,
        releaseDateGte: String?
    ): Result<List<TvShow>> {
        val request = DiscoverRequest().apply {
            this.page = page
            this.sortBy = sortBy
            this.releaseDateLte = releaseDateLte ?: getCurrentDateUseCase.invoke("yyyy-MM-dd")
            this.releaseDateGte = releaseDateGte
            this.language = getLanguageUseCase.invoke()
        }
        val data = TvShow()
        return repository.fetchAll(request, data)
    }
}