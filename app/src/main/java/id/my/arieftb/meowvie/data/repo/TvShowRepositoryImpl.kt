package id.my.arieftb.meowvie.data.repo

import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.data.remote.tv_show.TvShowRemoteDataSource
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.tv_show.TvShow
import id.my.arieftb.meowvie.domain.repo.TvShowRepository

class TvShowRepositoryImpl constructor(val remote: TvShowRemoteDataSource) : TvShowRepository {
    override suspend fun fetchAll(request: DiscoverRequest, data: TvShow): Result<List<TvShow>> {
        val response = remote.fetchAll(request)
        if (response.isSuccessful) {
            if (response.code() == 200) {
                if (response.body()?.tvShowResults != null) {
                    return Result.Success(
                        data = response.body()?.tvShowResults?.map {
                            data.mapFromResponse(it)
                        }?.toList()!!
                    )
                }
                return Result.Failure(Exception("404"))
            }
            return Result.Failure(Exception("${response.code()}"))
        }
        return Result.Failure(Exception("${response.code()}"))
    }
}