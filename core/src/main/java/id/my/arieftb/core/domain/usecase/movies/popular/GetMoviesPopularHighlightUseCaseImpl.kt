package id.my.arieftb.core.domain.usecase.movies.popular

import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.Content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMoviesPopularHighlightUseCaseImpl @Inject constructor(
    private val getMoviesPopularUseCase: GetMoviesPopularUseCase
) : GetMoviesPopularHighlightUseCase {
    override fun invoke(limit: Int): Flow<ResultEntity<List<Content>>> {
        return getMoviesPopularUseCase.invoke().map { result ->
            when (result) {
                is ResultEntity.Failure -> result
                is ResultEntity.Success -> ResultEntity.Success(data = result.data.take(limit))
            }
        }.flowOn(Dispatchers.IO)
    }
}