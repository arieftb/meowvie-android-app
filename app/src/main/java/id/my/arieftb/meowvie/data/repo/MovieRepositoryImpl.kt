package id.my.arieftb.meowvie.data.repo

import id.my.arieftb.meowvie.domain.model.request.detail.DetailRequest
import id.my.arieftb.meowvie.domain.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.data.remote.movie.MovieRemoteDataSource
import id.my.arieftb.meowvie.domain.model.entity.Result
import id.my.arieftb.meowvie.domain.model.entity.base.Content
import id.my.arieftb.meowvie.domain.model.entity.base.ContentDetail
import id.my.arieftb.meowvie.domain.repo.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remote: MovieRemoteDataSource
) :
    MovieRepository {
    override fun fetchAll(request: DiscoverRequest): Flow<Result<List<Content>>> {
        return remote.fetchAll(request).map { response ->
            if (response.isSuccessful) {
                if (response.body() != null) {
                    Result.Success(data = response.body()?.movieResults?.map {
                        it.toMovie()
                    }?.toList() ?: emptyList())
                } else {
                    Result.Success(emptyList())
                }
            } else {
                Result.Failure(Exception("Something went wrong"))
            }
        }
    }

    override fun fetch(request: DetailRequest): Flow<Result<ContentDetail>> {
        return remote.fetch(request).map { response ->
            if (response.isSuccessful) {
                if (response.body() != null) {
                    Result.Success(data = response.body()!!.toMovieDetail())
                } else {
                    Result.Failure(Exception("Something went wrong"))
                }
            } else {
                Result.Failure(Exception("Something went wrong"))
            }
        }
    }

}