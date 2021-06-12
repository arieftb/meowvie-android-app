package id.my.arieftb.meowvie.domain.usecase.tv_shows

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import javax.inject.Inject

class GetTvShowsHighlightUseCaseImpl @Inject constructor(private val getTvShowsUseCase: GetTvShowsUseCase) :
    GetTvShowsHighlightUseCase {
    override suspend fun invoke(limit: Int): Result<List<Content>> {
        return when (val result = getTvShowsUseCase.invoke()) {
            is Result.Failure -> result
            is Result.Success -> {
                return Result.Success(data = result.data.take(limit))
            }
        }
    }
}