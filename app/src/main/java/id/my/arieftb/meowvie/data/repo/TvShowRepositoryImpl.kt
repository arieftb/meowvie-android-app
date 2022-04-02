package id.my.arieftb.meowvie.data.repo

import id.my.arieftb.meowvie.data.model.request.detail.DetailRequest
import id.my.arieftb.meowvie.data.model.request.discover.DiscoverRequest
import id.my.arieftb.meowvie.data.remote.tv_show.TvShowRemoteDataSource
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.model.base.ContentDetail
import id.my.arieftb.meowvie.domain.repo.TvShowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    val remote: TvShowRemoteDataSource
) : TvShowRepository {
    override fun fetchAll(request: DiscoverRequest): Flow<Result<List<Content>>> {
        return remote.fetchAll(request).map { response ->
            if (response.isSuccessful) {
                if (response.body() != null) {
                    Result.Success(
                        data = response.body()?.tvShowResults?.map {
                            it.toTvShow()
                        }?.toList() ?: emptyList()
                    )
                } else {
                    Result.Success(data = emptyList())
                }
            } else {
                Result.Failure(Exception("Something went wrong"))
            }
        }
//        val response = remote.fetchAll(request)
//        if (response.isSuccessful && response.body()?.tvShowResults != null) {
//            return Result.Success(
//                data = response.body()?.tvShowResults?.map {
//                    it.toTvShow()
//                }?.toList() ?: emptyList()
//            )
//        }
//        return Result.Failure(Exception("Something went wrong"))
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