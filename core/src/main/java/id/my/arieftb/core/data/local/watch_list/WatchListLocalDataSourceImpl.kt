package id.my.arieftb.core.data.local.watch_list

import androidx.paging.PagingSource
import id.my.arieftb.core.domain.constant.ContentType
import id.my.arieftb.core.data.model.entity.WatchListEntity
import id.my.arieftb.core.data.model.request.content.ContentSaveRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WatchListLocalDataSourceImpl @Inject constructor(
    private val dao: WatchListDao
) : WatchListLocalDataSource {
    override fun saveWatchList(request: ContentSaveRequest): Flow<Long> {
        val entity = WatchListEntity(
            null,
            request.id!!,
            request.title!!,
            request.banner ?: request.poster,
            request.poster,
            request.type.toString(),
            request.createdAt
        )

        return flow {
            emit(dao.insert(entity))
        }
    }

    override fun checkWatchList(code: Long, type: ContentType): Flow<WatchListEntity?> {
        return flow {
            emit(dao.findByCode(code, type.toString()))
        }
    }

    override fun deleteWatchList(code: Long, type: ContentType): Flow<Int> {
        return flow {
            emit(dao.deleteByCode(code, type.toString()))
        }
    }

    override fun fetchAllWatchList(): PagingSource<Int, WatchListEntity> {
        return dao.fetchAll()
    }

}