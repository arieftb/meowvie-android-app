package id.my.arieftb.meowvie.domain.usecase.movies

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.movie.Movie

interface GetMoviesPopularHighlightUseCase {
    suspend fun invoke(limit: Int = 6): Result<List<Movie>>
}