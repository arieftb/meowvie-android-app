package id.my.arieftb.meowvie.domain.usecase.tv_shows.upcoming

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTvShowsUpcomingHighlightImpl @Inject constructor(
    private val getTvShowsUpcomingUseCaseUseCase: GetTvShowsUpcomingUseCase
) : GetTvShowsUpcomingHighlightUseCase {
    override fun invoke(limit: Int): Flow<Result<List<Content>>> {
        return getTvShowsUpcomingUseCaseUseCase.invoke().map { result ->
            when (result) {
                is Result.Failure -> result
                is Result.Success -> Result.Success(data = result.data.take(limit))
            }
        }
    }
}