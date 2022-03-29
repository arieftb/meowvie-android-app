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
    override suspend fun fetchAll(request: DiscoverRequest): Result<List<Content>> {
        val response = remote.fetchAll(request)
        if (response.isSuccessful && response.body()?.tvShowResults != null) {
            return Result.Success(
                data = response.body()?.tvShowResults?.map {
                    it.toTvShow()
                }?.toList() ?: emptyList()
            )
        }
        return Result.Failure(Exception("Something went wrong"))
    }

    override suspend fun fetch(request: DetailRequest): Result<ContentDetail> {
        val response = remote.fetch(request)
        if (response.isSuccessful && response.body() != null && response.body()?.success == true) {
            return Result.Success(
                data = response.body()!!.toTvShowDetail()
            )
        }
        return Result.Failure(Exception("Something went wrong"))
    }

}