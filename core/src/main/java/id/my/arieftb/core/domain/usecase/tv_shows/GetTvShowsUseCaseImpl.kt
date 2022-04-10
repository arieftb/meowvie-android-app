package id.my.arieftb.core.domain.usecase.tv_shows

import id.my.arieftb.core.data.model.request.discover.DiscoverRequest
import id.my.arieftb.core.domain.model.Result
import id.my.arieftb.core.domain.model.base.Content
import id.my.arieftb.core.domain.repo.TvShowRepository
import id.my.arieftb.core.domain.usecase.date.GetCurrentDateUseCase
import id.my.arieftb.core.domain.usecase.language.GetLanguageUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.zip

class GetTvShowsUseCaseImpl constructor(
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val repository: TvShowRepository
) : GetTvShowsUseCase {
    override fun invoke(
        page: Int,
        sortBy: String?,
        releaseDateLte: String?,
        releaseDateGte: String?
    ): Flow<Result<List<Content>>> {
        return getCurrentDateUseCase.invoke("yyyy-MM-dd")
            .zip(getLanguageUseCase.invoke()) { date, lang ->
                val request = DiscoverRequest().apply {
                    this.page = page
                    this.sortBy = sortBy
                    this.releaseDateLte = releaseDateLte ?: date
                    this.releaseDateGte = releaseDateGte
                    this.language = lang
                }
                repository.fetchAll(request)
            }.flatMapConcat {
                it
            }
    }
}