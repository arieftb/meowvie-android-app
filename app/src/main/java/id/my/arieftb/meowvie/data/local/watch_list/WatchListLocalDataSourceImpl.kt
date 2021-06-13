package id.my.arieftb.meowvie.data.local.watch_list

import id.my.arieftb.meowvie.constant.ContentType
import id.my.arieftb.meowvie.data.model.entity.WatchListEntity
import id.my.arieftb.meowvie.data.model.request.content.ContentSaveRequest
import javax.inject.Inject

class WatchListLocalDataSourceImpl @Inject constructor(
    private val dao: WatchListDao
) : WatchListLocalDataSource {
    override suspend fun saveWatchList(request: ContentSaveRequest): Long {
        val entity = WatchListEntity(
            null,
            request.id!!,
            request.title!!,
            request.banner,
            request.poster,
            request.type.toString(),
            request.createdAt
        )
        return dao.insert(entity)
    }

    override suspend fun checkWatchList(code: Long, type: ContentType): WatchListEntity? {
        return dao.findByCode(code, type.toString())
    }

    override suspend fun deleteWatchList(code: Long, type: ContentType): Int {
        return dao.deleteByCode(code, type.toString())
    }

}