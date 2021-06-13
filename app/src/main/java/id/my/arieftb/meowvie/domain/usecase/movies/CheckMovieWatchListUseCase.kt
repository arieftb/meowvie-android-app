package id.my.arieftb.meowvie.domain.usecase.movies

import id.my.arieftb.meowvie.domain.model.Result

interface CheckMovieWatchListUseCase {
    suspend fun invoke(code: Long): Result<Boolean>
}