package id.my.arieftb.data.repo

import id.my.arieftb.data.remote.tv_show.TvShowRemoteDataSource
import id.my.arieftb.meowvie.domain.model.entity.Result
import id.my.arieftb.meowvie.domain.model.entity.base.Content
import id.my.arieftb.meowvie.domain.model.entity.base.ContentDetail
import id.my.arieftb.meowvie.domain.model.request.detail.DetailRequest
import id.my.arieftb.meowvie.domain.model.request.discover.DiscoverRequest
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
    }

    override fun fetch(request: DetailRequest): Flow<Result<ContentDetail>> {
        return remote.fetch(request).map { response ->
            if (response.isSuccessful && response.body() != null && response.body()?.success == true) {
                Result.Success(
                    data = response.body()!!.toTvShowDetail()
                )
            } else {
                Result.Failure(Exception("Something went wrong"))
            }
        }
    }

}