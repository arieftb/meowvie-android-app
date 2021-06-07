package id.my.arieftb.meowvie.data.local.date

import id.my.arieftb.meowvie.utils.helper.date.DateHelper
import javax.inject.Inject

class DateLocalDataSourceImpl @Inject constructor(private val dateHelper: DateHelper) :
    DateLocalDataSource {
    override suspend fun getCurrentDate(format: String): String? {
        return dateHelper.now().toPattern(format).getString()
    }

    override suspend fun getDateMonthAhead(format: String, monthInterval: Int): String? {
        return dateHelper.now().addMonth(monthInterval).getString()
    }
}