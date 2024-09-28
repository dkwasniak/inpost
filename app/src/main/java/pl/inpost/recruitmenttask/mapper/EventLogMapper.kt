package pl.inpost.recruitmenttask.mapper

import pl.inpost.domain.model.EventLog
import pl.inpost.recruitmenttask.model.EventLogUiModel

fun EventLog.toUiModel(): EventLogUiModel {
    return EventLogUiModel(
        name = this.name,
        date = this.date
    )
}