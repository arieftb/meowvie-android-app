package id.my.arieftb.meowvie.data.repo

import id.my.arieftb.meowvie.data.local.movie.MovieLocalDataSource
import id.my.arieftb.meowvie.data.model.request.content.ContentSaveRequest
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
    private val remote: MovieRemoteDataSource,
    private val local: MovieLocalDataSource
) :
    MovieRepository {
    override suspend fun fetchAll(request: DiscoverRequest, data: Movie): Result<List<Content>> {
        val response = remote.fetchAll(request)
        if (response.isSuccessful) {
            if (response.code() == 200) {
                if (response.body()?.movieResults != null) {
                    return Result.Success(data = response.body()?.movieResults?.map {
                        data.mapFromMovieResult(it)
                    }?.toList()!!)
                }
                return Result.Failure(Exception("404"))
            }
            return Result.Failure(Exception("${response.code()}"))
        }
        return Result.Failure(Exception("${response.code()}"))
    }

    override suspend fun fetch(request: DetailRequest, data: MovieDetail): Result<ContentDetail> {
        val response = remote.fetch(request)
        if (response.isSuccessful) {
            if (response.code() == 200) {
                if (response.body() != null) {
                    return Result.Success(data = data.mapFromMovieDetailResponse(response.body()))
                }
                return Result.Failure(Exception("404"))
            }
            return Result.Failure(Exception("${response.code()}"))
        }
        return Result.Failure(Exception("${response.code()}"))
    }

    override suspend fun saveWatchList(request: ContentSaveRequest): Result<Boolean> {
        val response = local.saveWatchList(request)
        if (response != -1L) {
            return Result.Success(data = true)
        }

        return Result.Failure(exception = Exception("400"))
    }

    override suspend fun checkWatchList(code: Long): Result<Boolean> {
        val response = local.checkWatchList(code)
        if (response != null) {
            return Result.Success(data = true)
        }

        return Result.Success(data = false)
    }

    override suspend fun removeWatchList(code: Long): Result<Boolean> {
        val response = local.deleteWatchList(code)
        if (response > 0) {
            return Result.Success(data = false)
        }

        return Result.Failure(exception = Exception("400"))
    }
}