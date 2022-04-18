package id.my.arieftb.core.domain.usecase.movies.highlight

import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.Content
import id.my.arieftb.core.domain.usecase.movies.GetMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMoviesHighlightUseCaseImpl @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase) :
    GetMoviesHighlightUseCase {
    override fun invoke(limit: Int): Flow<ResultEntity<List<Content>>> {
        return getMoviesUseCase.invoke().flowOn(Dispatchers.IO)
    }
}