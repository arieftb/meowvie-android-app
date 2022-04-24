package id.my.arieftb.core.domain.usecase.tv_shows

import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.Content
import kotlinx.coroutines.flow.Flow

interface GetTvShowsUseCase {
    fun invoke(
        page: Int = 1,
        sortBy: String? = "first_air_date.desc",
        releaseDateLte: String? = null,
        releaseDateGte: String? = null
    ): Flow<ResultEntity<List<Content>>>
}