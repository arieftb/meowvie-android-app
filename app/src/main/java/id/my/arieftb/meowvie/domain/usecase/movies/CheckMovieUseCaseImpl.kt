package id.my.arieftb.meowvie.domain.usecase.movies

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.repo.MovieRepository
import javax.inject.Inject

class CheckMovieUseCaseImpl @Inject constructor(private val repository: MovieRepository) :
    CheckMovieUseCase {

    override suspend fun invoke(code: Long): Result<Boolean> {
        return repository.check(code)
    }
}