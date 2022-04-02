package id.my.arieftb.data.local.date

import kotlinx.coroutines.flow.Flow

interface DateLocalDataSource {
    fun getCurrentDate(format: String): Flow<String?>
    fun getDateMonthAhead(format: String, monthInterval: Int): Flow<String?>
    fun getDateDayAhead(format: String, dayInterval: Int): Flow<String?>
    fun getCurrentDateTimeMillis(): Flow<Long?>
}