package id.my.arieftb.meowvie.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.my.arieftb.meowvie.BuildConfig
import id.my.arieftb.meowvie.domain.constant.ContentType
import id.my.arieftb.meowvie.data.constant.DataConstant
import id.my.arieftb.meowvie.domain.model.entity.base.Content

@Entity(tableName = DataConstant.WATCH_LIST_TABLE_NAME)
data class WatchListEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = -1,
    @ColumnInfo(name = DataConstant.CONTENT_ID_COLUMN_NAME)
    var code: Long,
    @ColumnInfo(name = DataConstant.CONTENT_TITLE_COLUMN_NAME)
    var title: String,
    @ColumnInfo(name = DataConstant.CONTENT_BANNER_COLUMN_NAME)
    var bannerPath: String? = null,
    @ColumnInfo(name = DataConstant.CONTENT_POSTER_COLUMN_NAME)
    var posterPath: String? = null,
    @ColumnInfo(name = DataConstant.CONTENT_TYPE_COLUMN_NAME)
    var type: String,
    @ColumnInfo(name = DataConstant.CONTENT_CREATED_AT_COLUMN_NAME)
    var createdAt: Long? = null
) : WatchListEntityMapper {
    override fun toContent(): Content {
        return Content(
            id = this.code,
            title = this.title
        ).apply {
            posterPath =
                BuildConfig.BASE_URL_IMAGE_PORTRAIT_BIG + this@WatchListEntity.posterPath
            bannerPath = if (this@WatchListEntity.bannerPath != null) {
                BuildConfig.BASE_URL_IMAGE_LANDSCAPE + this@WatchListEntity.bannerPath
            } else BuildConfig.BASE_URL_IMAGE_PORTRAIT + this@WatchListEntity.posterPath
            type = if (this@WatchListEntity.type == "tv") {
                ContentType.TV
            } else {
                ContentType.MOVIE
            }
        }
    }

}

interface WatchListEntityMapper {
    fun toContent(): Content
}