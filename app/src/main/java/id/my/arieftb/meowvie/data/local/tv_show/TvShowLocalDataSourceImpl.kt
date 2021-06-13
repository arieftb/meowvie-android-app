package id.my.arieftb.meowvie.data.local.tv_show

import javax.inject.Inject

class TvShowLocalDataSourceImpl @Inject constructor(
    private val dao: TvShowDao
): TvShowLocalDataSource {

    override suspend fun deleteWatchList(code: Long): Int {
        return dao.deleteByCode(code)
    }
}