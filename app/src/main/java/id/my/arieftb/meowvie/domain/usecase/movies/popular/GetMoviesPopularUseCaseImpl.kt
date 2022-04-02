package id.my.arieftb.meowvie.domain.usecase.movies.popular

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.usecase.date.GetCurrentDateUseCase
import id.my.arieftb.meowvie.domain.usecase.movies.GetMoviesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

class GetMoviesPopularUseCaseImpl @Inject constructor(
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val getMoviesUseCase: GetMoviesUseCase
) : GetMoviesPopularUseCase {
    override fun invoke(page: Int): Flow<Result<List<Content>>> {
        return getCurrentDateUseCase.invoke("yyyy-MM-dd").flatMapConcat { date ->
            getMoviesUseCase.invoke(
                page = page,
                sortBy = "popularity.desc",
                releaseDateLte = date
            )
        }
    }
}