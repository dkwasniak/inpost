package pl.inpost.data.local.converter

import androidx.room.TypeConverter
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

class ZonedDateTimeConverter {

    @TypeConverter
    fun fromZonedDateTime(zonedDateTime: ZonedDateTime?): String? {
        return zonedDateTime?.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)
    }

    @TypeConverter
    fun toZonedDateTime(value: String?): ZonedDateTime? {
        return value?.let { ZonedDateTime.parse(it) }
    }

}

class ZoneIdConverter {

    @TypeConverter
    fun fromZoneId(zoneId: ZoneId?): String? {
        return zoneId?.id
    }

    @TypeConverter
    fun toZoneId(zoneIdString: String?): ZoneId? {
        return zoneIdString?.let { ZoneId.of(it) }
    }
}