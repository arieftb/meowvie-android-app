package id.my.arieftb.core.data.repo

import id.my.arieftb.core.data.model.request.detail.DetailRequest
import id.my.arieftb.core.data.model.request.discover.DiscoverRequest
import id.my.arieftb.core.data.remote.movie.MovieRemoteDataSource
import id.my.arieftb.core.domain.model.Result
import id.my.arieftb.core.domain.model.base.Content
import id.my.arieftb.core.domain.model.base.ContentDetail
import id.my.arieftb.core.domain.repo.MovieRepository
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
                    if (response.body()!!.success == true) {
                        Result.Success(data = response.body()!!.toMovieDetail())
                    } else {
                        Result.Failure(Exception("Something went wrong"))
                    }
                } else {
                    Result.Failure(Exception("Something went wrong"))
                }
            } else {
                Result.Failure(Exception("Something went wrong"))
            }
        }
    }

}