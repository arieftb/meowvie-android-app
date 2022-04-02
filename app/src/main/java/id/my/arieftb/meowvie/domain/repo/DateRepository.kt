package id.my.arieftb.meowvie.domain.repo

import kotlinx.coroutines.flow.Flow

interface DateRepository {
    fun getCurrentDate(format: String): Flow<String?>
    fun getMonthAhead(format: String, monthInterval: Int): Flow<String?>
    fun getDateAhead(format: String, dateInterval: Int): Flow<String?>
    fun getCurrentDateTimeMillis(): Flow<Long?>
}