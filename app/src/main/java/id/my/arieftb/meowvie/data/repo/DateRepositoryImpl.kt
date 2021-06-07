package id.my.arieftb.meowvie.data.repo

import id.my.arieftb.meowvie.data.local.date.DateLocalDataSource
import id.my.arieftb.meowvie.domain.repo.DateRepository
import javax.inject.Inject

class DateRepositoryImpl @Inject constructor(private val local: DateLocalDataSource) :
    DateRepository {
    override suspend fun getCurrentDate(format: String): String? {
        return local.getCurrentDate(format)
    }

    override suspend fun getDateAhead(format: String, monthInterval: Int): String? {
        return local.getDateMonthAhead(format, monthInterval)
    }
}