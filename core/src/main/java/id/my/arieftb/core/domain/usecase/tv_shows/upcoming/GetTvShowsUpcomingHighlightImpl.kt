package id.my.arieftb.core.domain.usecase.tv_shows.upcoming

import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.Content
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTvShowsUpcomingHighlightImpl @Inject constructor(
    private val getTvShowsUpcomingUseCaseUseCase: GetTvShowsUpcomingUseCase
) : GetTvShowsUpcomingHighlightUseCase {
    override fun invoke(limit: Int): Flow<ResultEntity<List<Content>>> {
        return getTvShowsUpcomingUseCaseUseCase.invoke().map { result ->
            when (result) {
                is ResultEntity.Failure -> result
                is ResultEntity.Success -> ResultEntity.Success(data = result.data.take(limit))
            }
        }
    }
}