package id.my.arieftb.meowvie.domain.usecase.tv_shows

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.repo.TvShowRepository
import javax.inject.Inject

class CheckTvShowUseCaseImpl @Inject constructor(
    private val repository: TvShowRepository
) : CheckTvShowUseCase {
    override suspend fun invoke(code: Long): Result<Boolean> {
        return repository.check(code)
    }
}