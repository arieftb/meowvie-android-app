package id.my.arieftb.meowvie.domain.repo

import id.my.arieftb.meowvie.data.model.request.content.ContentSaveRequest
import id.my.arieftb.meowvie.data.model.request.detail.DetailRequest
import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.model.base.ContentDetail
import id.my.arieftb.meowvie.domain.model.movie.Movie
import id.my.arieftb.meowvie.domain.model.movie.MovieDetail

interface MovieRepository {
    suspend fun fetchAll(request: DiscoverRequest, data: Movie): Result<List<Content>>
    suspend fun fetch(request: DetailRequest, data: MovieDetail): Result<ContentDetail>
    suspend fun saveWatchList(request: ContentSaveRequest): Result<Boolean>
    suspend fun checkWatchList(code: Long): Result<Boolean>
}