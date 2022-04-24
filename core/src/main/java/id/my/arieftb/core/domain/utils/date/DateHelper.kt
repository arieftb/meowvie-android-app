package id.my.arieftb.core.domain.utils.date

import java.text.SimpleDateFormat
import java.util.*

class DateHelper {

    private var calendar: Calendar? = null
    private var dateTime: Date? = null
    private var dateTimeFormat: SimpleDateFormat? = null

    fun now(): DateHelper {
        calendar = Calendar.getInstance()
        dateTime = calendar?.time
        return this
    }

    fun fromDateString(date: String, format: String): DateHelper {
        dateTime = SimpleDateFormat(format, Locale.getDefault()).parse(date)
        return this
    }

    fun addMonth(monthInterval: Int): DateHelper {
        calendar?.add(Calendar.MONTH, monthInterval)
        dateTime = calendar?.time
        return this
    }

    fun addDay(dayInterval: Int): DateHelper {
        calendar?.add(Calendar.DATE, dayInterval)
        dateTime = calendar?.time
        return this
    }

    fun toPattern(dateTimePattern: String): DateHelper {
        dateTimeFormat = SimpleDateFormat(dateTimePattern, Locale.getDefault())
        return this
    }

    fun getString(): String? {
        return dateTimeFormat?.format(dateTime!!)
    }

    fun getMillis(): Long? {
        return dateTime?.time
    }

    companion object {
        var instance: DateHelper? = null

        fun instance(): DateHelper? {
            if (instance == null) {
                synchronized(DateHelper::class.java) {
                    if (instance == null) {
                        instance = DateHelper()
                        return instance
                    }
                }
            }
            return instance
        }
    }
}