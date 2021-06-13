package id.my.arieftb.meowvie.data.repo

import id.my.arieftb.meowvie.data.local.date.DateLocalDataSource
import id.my.arieftb.meowvie.domain.repo.DateRepository
import javax.inject.Inject

class DateRepositoryImpl @Inject constructor(private val local: DateLocalDataSource) :
    DateRepository {
    override suspend fun getCurrentDate(format: String): String? {
        return local.getCurrentDate(format)
    }

    override suspend fun getMonthAhead(format: String, monthInterval: Int): String? {
        return local.getDateMonthAhead(format, monthInterval)
    }

    override suspend fun getDateAhead(format: String, dateInterval: Int): String? {
        return local.getDateDayAhead(format, dateInterval)
    }

    override suspend fun getCurrentDateTimeMillis(): Long? {
        return local.getCurrentDateTimeMillis()
    }
}