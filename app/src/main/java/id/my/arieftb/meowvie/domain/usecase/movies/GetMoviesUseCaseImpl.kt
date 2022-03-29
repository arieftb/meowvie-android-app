package id.my.arieftb.meowvie.domain.usecase.movies

import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.model.movie.Movie
import id.my.arieftb.meowvie.domain.repo.MovieRepository
import id.my.arieftb.meowvie.domain.usecase.date.GetCurrentDateUseCase
import id.my.arieftb.meowvie.domain.usecase.language.GetLanguageUseCase
import javax.inject.Inject

class GetMoviesUseCaseImpl @Inject constructor(
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val repository: MovieRepository
) :
    GetMoviesUseCase {
    override suspend fun invoke(
        page: Int,
        sortBy: String?,
        releaseDateLte: String?,
        releaseDateGte: String?
    ): Result<List<Content>> {
        val request = DiscoverRequest().apply {
            this.page = page
            this.sortBy = sortBy
            this.releaseDateLte = releaseDateLte ?: getCurrentDateUseCase.invoke("yyyy-MM-dd")
            this.releaseDateGte = releaseDateGte
            this.language = getLanguageUseCase.invoke()
        }
        return repository.fetchAll(request)
    }
}