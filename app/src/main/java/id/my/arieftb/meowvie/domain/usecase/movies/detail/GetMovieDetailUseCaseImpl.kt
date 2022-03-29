package id.my.arieftb.meowvie.domain.usecase.movies.detail

import id.my.arieftb.meowvie.data.model.request.detail.DetailRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.ContentDetail
import id.my.arieftb.meowvie.domain.model.movie.MovieDetail
import id.my.arieftb.meowvie.domain.repo.MovieRepository
import id.my.arieftb.meowvie.domain.usecase.language.GetLanguageUseCase
import javax.inject.Inject

class GetMovieDetailUseCaseImpl @Inject constructor(
    private val getLanguageUseCase: GetLanguageUseCase,
    private val repository: MovieRepository
) : GetMovieDetailUseCase {

    override suspend fun invoke(id: Long): Result<ContentDetail> {
        val request = DetailRequest().apply {
            this.id = id
            this.language = getLanguageUseCase.invoke()
        }
        return repository.fetch(request)
    }
}