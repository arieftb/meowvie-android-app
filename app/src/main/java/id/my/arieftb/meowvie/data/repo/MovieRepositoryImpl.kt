package id.my.arieftb.meowvie.data.repo

import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.data.remote.movie.MovieRemoteDataSource
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.movie.Movie
import id.my.arieftb.meowvie.domain.repo.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(val remote: MovieRemoteDataSource) : MovieRepository {
    override suspend fun fetchAll(request: DiscoverRequest, data: Movie): Result<List<Movie>> {
        val response = remote.fetchAll(request)
        if (response.isSuccessful) {
            if (response.code() == 200) {
                if (response.body()?.movieResults != null) {
                    return Result.Success(data = response.body()?.movieResults?.map {
                        data.mapFromResponse(it)
                    }?.toList()!!)
                }
                return Result.Failure(Exception("404"))
            }
            return Result.Failure(Exception("${response.code()}"))
        }
        return Result.Failure(Exception("${response.code()}"))
    }
}