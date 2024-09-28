package pl.inpost.data.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import dagger.Reusable
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.Instant
import org.threeten.bp.ZoneOffset
import javax.inject.Inject

@Reusable
class ZonedDateTimeApiTypeAdapter @Inject constructor() {

    @FromJson
    fun toZonedDateTime(value: String): ZonedDateTime {
        return Instant.parse(value).atZone(ZoneOffset.UTC)
    }

    @ToJson
    fun fromZonedDateTime(date: ZonedDateTime?): String? = date?.toInstant()?.toString()
}
