package id.my.arieftb.meowvie.data.local.tv_show

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.my.arieftb.meowvie.data.constant.DataConstant
import id.my.arieftb.meowvie.data.model.entity.TvShowWatchListEntity

@Dao
interface TvShowDao {
    @Query("DELETE FROM ${DataConstant.TV_SHOW_TABLE_NAME} WHERE ${DataConstant.CONTENT_ID_COLUMN_NAME} = :code")
    suspend fun deleteByCode(code: Long): Int
}