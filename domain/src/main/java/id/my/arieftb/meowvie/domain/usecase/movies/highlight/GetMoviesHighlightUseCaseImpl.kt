package id.my.arieftb.meowvie.domain.usecase.movies.highlight

import id.my.arieftb.meowvie.domain.model.entity.Result
import id.my.arieftb.meowvie.domain.model.entity.base.Content
import id.my.arieftb.meowvie.domain.usecase.movies.GetMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMoviesHighlightUseCaseImpl @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase) :
    GetMoviesHighlightUseCase {
    override fun invoke(limit: Int): Flow<Result<List<Content>>> {
        return getMoviesUseCase.invoke().flowOn(Dispatchers.IO)
    }
}