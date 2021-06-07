package id.my.arieftb.meowvie.domain.repo

import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.tv_show.TvShow

interface TvShowRepository {
    suspend fun fetchAll(request: DiscoverRequest, data: TvShow): Result<List<TvShow>>
}