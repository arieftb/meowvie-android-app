package id.my.arieftb.meowvie.data.local.tv_show

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.my.arieftb.meowvie.data.constant.DataConstant
import id.my.arieftb.meowvie.data.model.entity.TvShowEntity

@Dao
interface TvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvShowEntity: TvShowEntity): Long

    @Query("SELECT * FROM ${DataConstant.TV_SHOW_TABLE_NAME} WHERE ${DataConstant.CONTENT_ID_COLUMN_NAME} = :code")
    suspend fun findByCode(code: Long): TvShowEntity
}