package id.my.arieftb.meowvie.data.local.tv_show

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import id.my.arieftb.meowvie.data.model.entity.TvShowEntity

@Dao
interface TvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvShowEntity: TvShowEntity): Long
}