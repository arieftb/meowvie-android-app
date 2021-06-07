package id.my.arieftb.meowvie.data.local.date

import id.my.arieftb.meowvie.utils.helper.date.DateHelper
import javax.inject.Inject

class DateLocalDataSourceImpl @Inject constructor(val dateHelper: DateHelper): DateLocalDataSource {
    override suspend fun getCurrentDate(format: String): String? {
        return dateHelper.now().toPattern(format).getString()
    }
}