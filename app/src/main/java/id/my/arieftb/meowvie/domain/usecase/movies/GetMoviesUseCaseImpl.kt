package id.my.arieftb.meowvie.domain.usecase.movies

import id.my.arieftb.meowvie.data.model.request.movie.MovieRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.movie.Movie
import id.my.arieftb.meowvie.domain.repo.MovieRepository
import javax.inject.Inject

class GetMoviesUseCaseImpl @Inject constructor(private val repository: MovieRepository) :
    GetMoviesUseCase {
    override suspend fun invoke(
        page: Int,
        sortBy: String?,
        releaseDateLte: String?,
        releaseDateGte: String?
    ): Result<List<Movie>> {
        val request = MovieRequest().apply {
            this.page = page
            this.sortBy = sortBy
            this.releaseDateLte = releaseDateLte
            this.releaseDateGte = releaseDateGte
        }
        val data = Movie()
        return repository.fetchAll(request, data)
    }
}