package id.my.arieftb.meowvie.data.local.movie

interface MovieLocalDataSource {
    suspend fun deleteWatchList(code: Long): Int
}