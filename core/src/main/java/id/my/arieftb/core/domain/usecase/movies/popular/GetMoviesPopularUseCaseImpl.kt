package id.my.arieftb.core.domain.usecase.movies.popular

import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.Content
import id.my.arieftb.core.domain.usecase.date.GetCurrentDateUseCase
import id.my.arieftb.core.domain.usecase.movies.GetMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMoviesPopularUseCaseImpl @Inject constructor(
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val getMoviesUseCase: GetMoviesUseCase
) : GetMoviesPopularUseCase {
    override fun invoke(page: Int): Flow<ResultEntity<List<Content>>> {
        return getCurrentDateUseCase.invoke("yyyy-MM-dd").flatMapConcat { date ->
            getMoviesUseCase.invoke(
                page = page,
                sortBy = "popularity.desc",
                releaseDateLte = date
            )
        }.flowOn(Dispatchers.IO)
    }
}