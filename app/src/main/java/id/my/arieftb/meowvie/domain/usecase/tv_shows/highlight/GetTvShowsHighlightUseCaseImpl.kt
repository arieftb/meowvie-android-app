package id.my.arieftb.meowvie.domain.usecase.tv_shows.highlight

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.usecase.tv_shows.GetTvShowsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTvShowsHighlightUseCaseImpl @Inject constructor(private val getTvShowsUseCase: GetTvShowsUseCase) :
    GetTvShowsHighlightUseCase {
    override fun invoke(limit: Int): Flow<Result<List<Content>>> {
        return getTvShowsUseCase.invoke().map { result ->
            when (result) {
                is Result.Failure -> result
                is Result.Success -> {
                    Result.Success(data = result.data.take(limit))
                }
            }
        }
    }
}