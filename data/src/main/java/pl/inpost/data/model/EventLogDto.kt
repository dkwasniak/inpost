package pl.inpost.data.model

import org.threeten.bp.ZonedDateTime


data class EventLogDto(
    val name: String,
    val date: ZonedDateTime
)
