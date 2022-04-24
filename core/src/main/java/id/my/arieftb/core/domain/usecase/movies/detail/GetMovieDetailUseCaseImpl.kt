package id.my.arieftb.core.domain.usecase.movies.detail

import id.my.arieftb.core.data.model.request.detail.DetailRequest
import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.ContentDetail
import id.my.arieftb.core.domain.repo.MovieRepository
import id.my.arieftb.core.domain.usecase.language.GetLanguageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovieDetailUseCaseImpl @Inject constructor(
    private val getLanguageUseCase: GetLanguageUseCase,
    private val repository: MovieRepository
) : GetMovieDetailUseCase {

    override fun invoke(id: Long): Flow<ResultEntity<ContentDetail>> {
        return getLanguageUseCase.invoke().flatMapConcat { lang ->
            val request = DetailRequest().apply {
                this.id = id
                this.language = lang
            }
            repository.fetch(request)
        }.flowOn(Dispatchers.IO)
    }
}