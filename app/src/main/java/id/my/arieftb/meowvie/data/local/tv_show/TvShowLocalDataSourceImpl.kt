package id.my.arieftb.meowvie.data.local.tv_show

import id.my.arieftb.meowvie.data.model.entity.TvShowWatchListEntity
import id.my.arieftb.meowvie.data.model.request.content.ContentSaveRequest
import javax.inject.Inject

class TvShowLocalDataSourceImpl @Inject constructor(
    private val dao: TvShowDao
): TvShowLocalDataSource {
    override suspend fun checkWatchList(code: Long): TvShowWatchListEntity? {
        return dao.findByCode(code)
    }

    override suspend fun deleteWatchList(code: Long): Int {
        return dao.deleteByCode(code)
    }
}