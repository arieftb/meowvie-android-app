package id.my.arieftb.meowvie.domain.repo

import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.tv_show.TvShow
import id.my.arieftb.meowvie.domain.model.tv_show.TvShowDetail

interface TvShowRepository {
    suspend fun fetchAll(request: DiscoverRequest, data: TvShow): Result<List<TvShow>>
    suspend fun fetch(request: DiscoverRequest, data: TvShowDetail): Result<TvShowDetail>
}