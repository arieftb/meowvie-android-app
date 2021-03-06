package id.my.arieftb.core.domain.usecase.movies

import id.my.arieftb.core.data.model.request.discover.DiscoverRequest
import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.Content
import id.my.arieftb.core.domain.repo.MovieRepository
import id.my.arieftb.core.domain.usecase.date.GetCurrentDateUseCase
import id.my.arieftb.core.domain.usecase.language.GetLanguageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class GetMoviesUseCaseImpl @Inject constructor(
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val repository: MovieRepository
) :
    GetMoviesUseCase {
    @OptIn(FlowPreview::class)
    override fun invoke(
        page: Int,
        sortBy: String?,
        releaseDateLte: String?,
        releaseDateGte: String?
    ): Flow<ResultEntity<List<Content>>> {
        return getCurrentDateUseCase.invoke("yyyy-MM-dd")
            .zip(getLanguageUseCase.invoke()) { dateResult, langResult ->
                repository.fetchAll(DiscoverRequest().apply {
                    this.page = page
                    this.sortBy = sortBy
                    this.releaseDateLte =
                        releaseDateLte ?: dateResult
                    this.releaseDateGte = releaseDateGte
                    this.language = langResult
                })
            }.flatMapConcat {
                it
            }.flowOn(Dispatchers.IO)
    }
}