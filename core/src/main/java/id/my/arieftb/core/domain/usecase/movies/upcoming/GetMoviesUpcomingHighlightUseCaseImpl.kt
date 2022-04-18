package id.my.arieftb.core.domain.usecase.movies.upcoming

import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.Content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMoviesUpcomingHighlightUseCaseImpl @Inject constructor(private val getMoviesUpcomingUseCase: GetMoviesUpcomingUseCase) :
    GetMoviesUpcomingHighlightUseCase {
    override fun invoke(limit: Int): Flow<ResultEntity<List<Content>>> {
        return getMoviesUpcomingUseCase.invoke().map { result ->
            when (result) {
                is ResultEntity.Failure -> result
                is ResultEntity.Success -> ResultEntity.Success(data = result.data.take(limit))
            }
        }.flowOn(Dispatchers.IO)
    }
}