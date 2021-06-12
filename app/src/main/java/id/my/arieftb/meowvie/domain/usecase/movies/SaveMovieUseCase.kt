package id.my.arieftb.meowvie.domain.usecase.movies

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content

interface SaveMovieUseCase {
    suspend fun invoke(content: Content): Result<Boolean>
}