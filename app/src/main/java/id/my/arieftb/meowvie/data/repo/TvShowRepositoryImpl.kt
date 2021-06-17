package id.my.arieftb.meowvie.data.repo

import id.my.arieftb.meowvie.data.model.request.detail.DetailRequest
import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.data.remote.tv_show.TvShowRemoteDataSource
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.model.base.ContentDetail
import id.my.arieftb.meowvie.domain.model.tv_show.TvShow
import id.my.arieftb.meowvie.domain.model.tv_show.TvShowDetail
import id.my.arieftb.meowvie.domain.repo.TvShowRepository
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    val remote: TvShowRemoteDataSource
) : TvShowRepository {
    override suspend fun fetchAll(request: DiscoverRequest, data: TvShow): Result<List<Content>> {
        val response = remote.fetchAll(request)
        if (response.isSuccessful) {
            if (response.code() == 200) {
                if (response.body()?.tvShowResults != null) {
                    return Result.Success(
                        data = response.body()?.tvShowResults?.map {
                            data.mapFromTvShowResult(it)
                        }?.toList()!!
                    )
                }
                return Result.Failure(Exception("Something went wrong"))
            }
            return Result.Failure(Exception("Something went wrong"))
        }
        return Result.Failure(Exception("Something went wrong"))
    }

    override suspend fun fetch(request: DetailRequest, data: TvShowDetail): Result<ContentDetail> {
        val response = remote.fetch(request)
        if (response.isSuccessful) {
            if (response.code() == 200) {
                if (response.body() != null) {
                    return Result.Success(
                        data = data.mapFromTvShowDetailResponse(response.body())
                    )
                }
                return Result.Failure(Exception("404"))
            }
            return Result.Failure(Exception("${response.code()}"))
        }
        return Result.Failure(Exception("${response.code()}"))
    }

}