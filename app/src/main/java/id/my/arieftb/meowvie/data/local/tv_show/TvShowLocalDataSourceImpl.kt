package id.my.arieftb.meowvie.data.local.tv_show

import id.my.arieftb.meowvie.data.model.entity.TvShowEntity
import id.my.arieftb.meowvie.data.model.request.content.ContentSaveRequest
import javax.inject.Inject

class TvShowLocalDataSourceImpl @Inject constructor(
    private val tvShowDao: TvShowDao
): TvShowLocalDataSource {
    override suspend fun saveTvShow(request: ContentSaveRequest): Long {
        val entity = TvShowEntity(
            null,
            request.id!!,
            request.title!!,
            request.banner,
            request.poster,
            request.type.toString(),
            request.createdAt
        )
        return tvShowDao.insert(entity)
    }
}