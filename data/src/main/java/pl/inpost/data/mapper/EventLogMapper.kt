package pl.inpost.data.mapper

import pl.inpost.data.model.EventLogDto
import pl.inpost.domain.model.EventLog

fun EventLogDto.toDomain(): EventLog {
    return EventLog(
        name = this.name,
        date = this.date
    )
}