package id.my.arieftb.meowvie.data.repo

import id.my.arieftb.meowvie.data.model.request.detail.DetailRequest
import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.data.remote.movie.MovieRemoteDataSource
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.model.base.ContentDetail
import id.my.arieftb.meowvie.domain.model.movie.Movie
import id.my.arieftb.meowvie.domain.model.movie.MovieDetail
import id.my.arieftb.meowvie.domain.repo.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remote: MovieRemoteDataSource
) :
    MovieRepository {
    override suspend fun fetchAll(request: DiscoverRequest, data: Movie): Result<List<Content>> {
        val response = remote.fetchAll(request)
        if (response.isSuccessful) {
            if (response.body() != null) {
                return Result.Success(data = response.body()?.movieResults?.map {
                    data.mapFromMovieResult(it)
                }?.toList() ?: emptyList())
            }
            return Result.Failure(Exception("Something went wrong"))
        }
        return Result.Failure(Exception("Something went wrong"))
    }

    override suspend fun fetch(request: DetailRequest, data: MovieDetail): Result<ContentDetail> {
        val response = remote.fetch(request)
        if (response.isSuccessful) {
            if (response.body() != null) {
                return Result.Success(data = data.mapFromMovieDetailResponse(response.body()))
            }
            return Result.Failure(Exception("Something went wrong"))
        }
        return Result.Failure(Exception("Something went wrong"))
    }

}