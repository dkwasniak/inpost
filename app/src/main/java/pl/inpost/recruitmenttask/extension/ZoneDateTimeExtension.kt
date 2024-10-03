package pl.inpost.recruitmenttask.extension

import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.Locale


fun ZonedDateTime?.toDisplayString(): String? {
    if (this == null) {
        return null
    }

    val formatter = DateTimeFormatter.ofPattern("EEE | dd.MM.yyyy | HH:mm", Locale.getDefault())

    return this.format(formatter)
}