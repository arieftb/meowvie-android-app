package id.my.arieftb.core.domain.usecase.tv_shows.popular

import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.Content
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTvShowsPopularHighlightUseCaseImpl @Inject constructor(
    private val getTvShowsPopularUseCase: GetTvShowsPopularUseCase
) : GetTvShowsPopularHighlightUseCase {
    override fun invoke(limit: Int): Flow<ResultEntity<List<Content>>> {
        return getTvShowsPopularUseCase.invoke().map { result ->
            when (result) {
                is ResultEntity.Success -> ResultEntity.Success(data = result.data.take(limit))
                else -> result
            }
        }
    }
}