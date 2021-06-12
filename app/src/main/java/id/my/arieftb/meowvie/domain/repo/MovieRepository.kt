package id.my.arieftb.meowvie.domain.repo

import id.my.arieftb.meowvie.data.model.request.detail.DetailRequest
import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.movie.Movie
import id.my.arieftb.meowvie.domain.model.movie.MovieDetail

interface MovieRepository {
    suspend fun fetchAll(request: DiscoverRequest, data: Movie): Result<List<Movie>>
    suspend fun fetch(request: DetailRequest, data: MovieDetail): Result<MovieDetail>
}