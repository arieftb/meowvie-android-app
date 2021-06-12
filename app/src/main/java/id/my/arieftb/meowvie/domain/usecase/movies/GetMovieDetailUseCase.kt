package id.my.arieftb.meowvie.domain.usecase.movies

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.movie.MovieDetail

interface GetMovieDetailUseCase {
    suspend fun invoke(id: Long): Result<MovieDetail>
}