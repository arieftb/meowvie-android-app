package id.my.arieftb.meowvie.domain.usecase.tv_shows.detail

import id.my.arieftb.meowvie.domain.model.entity.Result
import id.my.arieftb.meowvie.domain.model.entity.base.ContentDetail
import id.my.arieftb.meowvie.domain.model.request.detail.DetailRequest
import id.my.arieftb.meowvie.domain.repo.TvShowRepository
import id.my.arieftb.meowvie.domain.usecase.language.GetLanguageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetTvShowDetailUseCaseImpl @Inject constructor(
    private val getLanguageUseCase: GetLanguageUseCase,
    private val repository: TvShowRepository
) : GetTvShowDetailUseCase {
    override fun invoke(id: Long): Flow<Result<ContentDetail>> {
        return getLanguageUseCase.invoke().flatMapConcat { lang ->
            val request = DetailRequest().apply {
                this.id = id
                this.language = lang
            }
            repository.fetch(request)
        }.flowOn(Dispatchers.IO)

    }
}