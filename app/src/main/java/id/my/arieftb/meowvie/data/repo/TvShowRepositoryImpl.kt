package id.my.arieftb.meowvie.data.repo

import id.my.arieftb.meowvie.data.local.tv_show.TvShowLocalDataSource
import id.my.arieftb.meowvie.data.model.request.content.ContentSaveRequest
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
    val remote: TvShowRemoteDataSource,
    val local: TvShowLocalDataSource
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
                return Result.Failure(Exception("404"))
            }
            return Result.Failure(Exception("${response.code()}"))
        }
        return Result.Failure(Exception("${response.code()}"))
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
}