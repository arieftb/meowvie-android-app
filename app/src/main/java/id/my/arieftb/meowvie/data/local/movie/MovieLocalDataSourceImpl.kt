package id.my.arieftb.meowvie.data.local.movie

import id.my.arieftb.meowvie.data.model.entity.MovieWatchListEntity
import id.my.arieftb.meowvie.data.model.request.content.ContentSaveRequest
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val dao: MovieDao
) : MovieLocalDataSource {
    override suspend fun checkWatchList(code: Long): MovieWatchListEntity? {
        return dao.findByCode(code)
    }

    override suspend fun deleteWatchList(code: Long): Int {
        return dao.deleteByCode(code)
    }
}