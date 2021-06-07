package id.my.arieftb.meowvie.data.local.date

interface DateLocalDataSource {
    suspend fun getCurrentDate(format: String): String?
    suspend fun getDateMonthAhead(format: String, monthInterval: Int): String?
}