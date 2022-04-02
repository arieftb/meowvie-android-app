package id.my.arieftb.meowvie.domain.usecase.tv_shows

import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import kotlinx.coroutines.flow.Flow

interface GetTvShowsUseCase {
    fun invoke(
        page: Int = 1,
        sortBy: String? = "first_air_date.desc",
        releaseDateLte: String? = null,
        releaseDateGte: String? = null
    ): Flow<Result<List<Content>>>
}