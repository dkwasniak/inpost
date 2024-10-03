package pl.inpost.data.local.converter

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import pl.inpost.data.model.EventLogDto

class EventLogListConverter {

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(ZonedDateTimeAdapter()) // Dodaj adapter dla ZonedDateTime
        .add(ZoneIdAdapter())
        .build()

    private val eventLogDtoAdapter: JsonAdapter<List<EventLogDto>> =
        moshi.adapter(Types.newParameterizedType(List::class.java, EventLogDto::class.java))

    @TypeConverter
    fun fromEventLogList(eventLog: List<EventLogDto>?): String {
        return eventLogDtoAdapter.toJson(eventLog)
    }

    @TypeConverter
    fun toEventLogList(eventLogString: String): List<EventLogDto>? {
        return eventLogDtoAdapter.fromJson(eventLogString)
    }
}