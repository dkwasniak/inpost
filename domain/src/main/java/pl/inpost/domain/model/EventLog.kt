package pl.inpost.domain.model

import org.threeten.bp.ZonedDateTime


data class EventLog(
    val name: String,
    val date: ZonedDateTime
)
