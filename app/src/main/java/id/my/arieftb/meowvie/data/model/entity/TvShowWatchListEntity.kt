package id.my.arieftb.meowvie.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.my.arieftb.meowvie.data.constant.DataConstant

@Entity(tableName = DataConstant.TV_SHOW_TABLE_NAME)
class TvShowWatchListEntity(
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
)