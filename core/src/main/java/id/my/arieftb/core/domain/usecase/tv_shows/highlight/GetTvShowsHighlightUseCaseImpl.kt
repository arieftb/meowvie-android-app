package id.my.arieftb.core.domain.usecase.tv_shows.highlight

import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.Content
import id.my.arieftb.core.domain.usecase.tv_shows.GetTvShowsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTvShowsHighlightUseCaseImpl @Inject constructor(private val getTvShowsUseCase: GetTvShowsUseCase) :
    GetTvShowsHighlightUseCase {
    override fun invoke(limit: Int): Flow<ResultEntity<List<Content>>> {
        return getTvShowsUseCase.invoke().map { result ->
            when (result) {
                is ResultEntity.Failure -> result
                is ResultEntity.Success -> {
                    ResultEntity.Success(data = result.data.take(limit))
                }
            }
        }
    }
}