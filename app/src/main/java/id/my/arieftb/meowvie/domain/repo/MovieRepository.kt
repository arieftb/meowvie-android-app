package id.my.arieftb.meowvie.domain.repo

import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.movie.Movie

interface MovieRepository {
    suspend fun fetchAll(request: DiscoverRequest, data: Movie): Result<List<Movie>>
}