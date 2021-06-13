package id.my.arieftb.meowvie.domain.usecase.tv_shows

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.repo.TvShowRepository
import javax.inject.Inject

class DeleteTvShowWatchListUseCaseImpl @Inject constructor(
    private val repository: TvShowRepository
): DeleteTvShowWatchListUseCase {
    override suspend fun invoke(code: Long): Result<Boolean> {
        return repository.removeWatchList(code)
    }
}