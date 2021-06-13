package id.my.arieftb.meowvie.data.local.movie

import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val dao: MovieDao
) : MovieLocalDataSource {
    override suspend fun deleteWatchList(code: Long): Int {
        return dao.deleteByCode(code)
    }
}