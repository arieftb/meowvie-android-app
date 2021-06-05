package id.my.arieftb.meowvie.data.repo

import id.my.arieftb.meowvie.data.model.request.movie.MovieRequest
import id.my.arieftb.meowvie.data.remote.movie.MovieRemoteDataSource
import id.my.arieftb.meowvie.domain.model.movie.Movie
import id.my.arieftb.meowvie.domain.repo.MovieRepository
import javax.inject.Inject
import id.my.arieftb.meowvie.domain.model.Result

class MovieRepositoryImpl @Inject constructor(val remote: MovieRemoteDataSource) : MovieRepository {
    override suspend fun fetchAll(request: MovieRequest, data: Movie): Result<List<Movie>> {
        val response = remote.fetchAll(request)
        if (response.isSuccessful) {
            if (response.code() == 200) {
                if (response.body()?.movieResults != null) {
                    return Result.Success(data = response.body()?.movieResults?.map {
                        data.mapFromResponse(it)
                    }?.toList()!!)
                }
            }
            return Result.Failure(Exception("${response.code()}"))
        }
        return Result.Failure(Exception("${response.code()}"))
    }
}