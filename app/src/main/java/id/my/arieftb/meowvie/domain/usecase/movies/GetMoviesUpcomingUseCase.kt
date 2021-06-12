package id.my.arieftb.meowvie.domain.usecase.movies

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content

interface GetMoviesUpcomingUseCase {
    suspend fun invoke(): Result<List<Content>>
}