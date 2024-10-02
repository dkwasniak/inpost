package pl.inpost.recruitmenttask.model

import org.threeten.bp.ZonedDateTime


data class EventLogUiModel(
    val name: String,
    val date: ZonedDateTime
)
