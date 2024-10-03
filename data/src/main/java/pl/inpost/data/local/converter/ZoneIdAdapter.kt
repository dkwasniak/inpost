package pl.inpost.data.local.converter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.ZoneId

class ZoneIdAdapter {

    @ToJson
    fun toJson(zoneId: ZoneId?): String? {
        return zoneId?.id
    }

    @FromJson
    fun fromJson(value: String?): ZoneId? {
        return value?.let { ZoneId.of(it) }
    }
}