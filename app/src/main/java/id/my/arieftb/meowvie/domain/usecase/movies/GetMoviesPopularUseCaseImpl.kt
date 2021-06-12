package id.my.arieftb.meowvie.domain.usecase.movies

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.usecase.date.GetCurrentDateUseCase
import javax.inject.Inject

class GetMoviesPopularUseCaseImpl @Inject constructor(
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val getMoviesUseCase: GetMoviesUseCase
) : GetMoviesPopularUseCase {
    override suspend fun invoke(page: Int): Result<List<Content>> {
        return getMoviesUseCase.invoke(
            page = page,
            sortBy = "popularity.desc",
            releaseDateLte = getCurrentDateUseCase.invoke("yyyy-MM-dd")
        )
    }
}