package id.my.arieftb.meowvie.data.local.tv_show

interface TvShowLocalDataSource {
    suspend fun deleteWatchList(code: Long): Int
}