package pl.inpost.data.local.converter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

class ZonedDateTimeAdapter {

    @ToJson
    fun toJson(zonedDateTime: ZonedDateTime?): String? {
        return zonedDateTime?.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)
    }

    @FromJson
    fun fromJson(value: String?): ZonedDateTime? {
        return value?.let { ZonedDateTime.parse(it) }
    }
}