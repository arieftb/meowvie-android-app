package id.my.arieftb.meowvie.domain.repo

interface DateRepository {
    suspend fun getCurrentDate(format: String): String?
    suspend fun getMonthAhead(format: String, monthInterval: Int): String?
    suspend fun getDateAhead(format: String, dateInterval: Int): String?
}