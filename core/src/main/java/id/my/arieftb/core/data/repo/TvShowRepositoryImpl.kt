package id.my.arieftb.core.data.repo

import id.my.arieftb.core.data.model.request.detail.DetailRequest
import id.my.arieftb.core.data.model.request.discover.DiscoverRequest
import id.my.arieftb.core.data.remote.tv_show.TvShowRemoteDataSource
import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.Content
import id.my.arieftb.core.domain.model.base.ContentDetail
import id.my.arieftb.core.domain.repo.TvShowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    val remote: TvShowRemoteDataSource
) : TvShowRepository {
    override fun fetchAll(request: DiscoverRequest): Flow<ResultEntity<List<Content>>> {
        return remote.fetchAll(request).map { response ->
            if (response.isSuccessful) {
                if (response.body() != null) {
                    ResultEntity.Success(
                        data = response.body()?.tvShowResults?.map {
                            it.toTvShow()
                        }?.toList() ?: emptyList()
                    )
                } else {
                    ResultEntity.Success(data = emptyList())
                }
            } else {
                ResultEntity.Failure(Exception("Something went wrong"))
            }
        }
    }

    override fun fetch(request: DetailRequest): Flow<ResultEntity<ContentDetail>> {
        return remote.fetch(request).map { response ->
            if (response.isSuccessful && response.body() != null && response.body()?.success == true) {
                ResultEntity.Success(data = response.body()!!.toTvShowDetail())
            } else {
                ResultEntity.Failure(Exception("Something went wrong"))
            }
        }
    }

}