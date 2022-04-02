package id.my.arieftb.meowvie.data.local.date

import id.my.arieftb.meowvie.domain.utils.date.DateHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DateLocalDataSourceImpl @Inject constructor(private val dateHelper: DateHelper) :
    DateLocalDataSource {
    override fun getCurrentDate(format: String): Flow<String?> {
        return flow {
            emit(dateHelper.now().toPattern(format).getString())
        }
    }

    override fun getDateMonthAhead(format: String, monthInterval: Int): Flow<String?> {
        return flow {
            emit(dateHelper.now().toPattern(format).addMonth(monthInterval).getString())
        }
    }

    override fun getDateDayAhead(format: String, dayInterval: Int): Flow<String?> {
        return flow {
            emit(dateHelper.now().toPattern(format).addDay(dayInterval).getString())
        }
    }

    override fun getCurrentDateTimeMillis(): Flow<Long?> {
        return flow {
            emit(dateHelper.now().getMillis())
        }
    }
}