package id.my.arieftb.meowvie.data.repo

import androidx.lifecycle.LiveData
import androidx.paging.*
import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.data.local.watch_list.WatchListLocalDataSource
import id.my.arieftb.meowvie.data.model.entity.WatchListEntity
import id.my.arieftb.meowvie.data.model.request.content.ContentSaveRequest
import id.my.arieftb.meowvie.domain.model.Result
import id.my.arieftb.meowvie.domain.repo.WatchListRepository
import kotlinx.coroutines.flow.Flow
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

    override fun fetchAllWatchList(limit: Int): Flow<PagingData<WatchListEntity>> {
//        val config = PagedList.Config.Builder()
//            .setEnablePlaceholders(false)
//            .setInitialLoadSizeHint(limit)
//            .setPageSize(limit)
//            .build()
        return Pager(
            PagingConfig(
                pageSize = limit,
                enablePlaceholders = true
            )
        ) {
            local.fetchAllWatchList()
        }.flow
    }
}