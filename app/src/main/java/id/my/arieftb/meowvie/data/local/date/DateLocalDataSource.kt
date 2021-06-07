package id.my.arieftb.meowvie.data.local.date

interface DateLocalDataSource {
    suspend fun getCurrentDate(format: String): String?
}