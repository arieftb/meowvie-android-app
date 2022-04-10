package id.my.arieftb.core.data.local.watch_list

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.my.arieftb.core.data.constant.DataConstant
import id.my.arieftb.core.data.model.entity.WatchListEntity

@Dao
interface WatchListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(watchListEntity: WatchListEntity): Long

    @Query("SELECT * FROM ${DataConstant.WATCH_LIST_TABLE_NAME} WHERE ${DataConstant.CONTENT_ID_COLUMN_NAME} = :code AND ${DataConstant.CONTENT_TYPE_COLUMN_NAME} = :type")
    suspend fun findByCode(code: Long, type: String): WatchListEntity

    @Query("DELETE FROM ${DataConstant.WATCH_LIST_TABLE_NAME} WHERE ${DataConstant.CONTENT_ID_COLUMN_NAME} = :code AND ${DataConstant.CONTENT_TYPE_COLUMN_NAME} = :type")
    suspend fun deleteByCode(code: Long, type: String): Int

    @Query("SELECT * FROM ${DataConstant.WATCH_LIST_TABLE_NAME}")
    fun fetchAll(): PagingSource<Int, WatchListEntity>
}