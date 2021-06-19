package id.my.arieftb.meowvie.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.data.local.watch_list.WatchListLocalDataSource
import id.my.arieftb.meowvie.data.model.request.content.ContentSaveRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.model.base.Content
import id.my.arieftb.meowvie.domain.repo.WatchListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WatchListRepositoryImpl @Inject constructor(
    private val local: WatchListLocalDataSource
) : WatchListRepository {
    override suspend fun saveWatchList(request: ContentSaveRequest): Result<Boolean> {
        val response = local.saveWatchList(request)
        if (response != -1L) {
            return Result.Success(data = true)
        }

        return Result.Failure(exception = Exception("Something went wrong"))
    }

    override suspend fun checkWatchList(code: Long, type: ContentType): Result<Boolean> {
        val response = local.checkWatchList(code, type)
        if (response != null && response.code == code && response.type == type.toString()) {
            return Result.Success(data = true)
        }

        return Result.Success(data = false)
    }

    override suspend fun removeWatchList(code: Long, type: ContentType): Result<Boolean> {
        val response = local.deleteWatchList(code, type)
        if (response > 0) {
            return Result.Success(data = false)
        }

        return Result.Failure(exception = Exception("Something went wrong"))
    }

    override fun fetchAllWatchList(limit: Int, data: Content): Flow<PagingData<Content>> {
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
                data.mapFromWatchlistResult(entity)
            }
        }
    }
}