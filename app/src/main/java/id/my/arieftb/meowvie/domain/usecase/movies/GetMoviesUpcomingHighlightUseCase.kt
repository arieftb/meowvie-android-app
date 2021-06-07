package id.my.arieftb.meowvie.domain.usecase.movies

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.movie.Movie

interface GetMoviesUpcomingHighlightUseCase {
    suspend fun invoke(limit: Int = 4): Result<List<Movie>>
}