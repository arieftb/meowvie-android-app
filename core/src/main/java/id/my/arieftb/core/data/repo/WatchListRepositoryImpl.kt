package id.my.arieftb.core.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import id.my.arieftb.core.domain.constant.ContentType
import id.my.arieftb.core.data.local.watch_list.WatchListLocalDataSource
import id.my.arieftb.core.data.model.request.content.ContentSaveRequest
import id.my.arieftb.core.domain.model.ResultEntity
import id.my.arieftb.core.domain.model.base.Content
import id.my.arieftb.core.domain.repo.WatchListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WatchListRepositoryImpl @Inject constructor(
    private val local: WatchListLocalDataSource
) : WatchListRepository {
    override fun saveWatchList(request: ContentSaveRequest): Flow<ResultEntity<Boolean>> {
        return local.saveWatchList(request).map { response ->
            if (response != -1L) {
                ResultEntity.Success(true)
            } else {
                ResultEntity.Failure(exception = Exception("Something went wrong"))
            }
        }
    }

    override fun checkWatchList(code: Long, type: ContentType): Flow<ResultEntity<Boolean>> {
        return local.checkWatchList(code, type).map { response ->
            if (response != null && response.code == code && response.type == type.toString()) {
                ResultEntity.Success(data = true)
            } else {
                ResultEntity.Success(data = false)
            }
        }
    }

    override fun removeWatchList(code: Long, type: ContentType): Flow<ResultEntity<Boolean>> {
        return local.deleteWatchList(code, type).map { response ->
            if (response > 0) {
                ResultEntity.Success(data = false)
            } else {
                ResultEntity.Failure(exception = Exception("Something went wrong"))
            }
        }
    }

    override fun fetchAllWatchList(limit: Int): Flow<PagingData<Content>> {
        return Pager(
            PagingConfig(
                pageSize = limit,
                initialLoadSize = limit,
                enablePlaceholders = true
            )
        ) {
            local.fetchAllWatchList()
        }.flow.map {
            it.map { entity ->
                entity.toContent()
            }
        }
    }
}