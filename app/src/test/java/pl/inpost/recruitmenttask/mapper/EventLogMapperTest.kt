package pl.inpost.recruitmenttask.mapper

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.inpost.domain.model.EventLog
import java.time.ZonedDateTime

class EventLogUiMapperTest {

    @Test
    fun `should map EventLog to EventLogUiModel correctly`() {
        // Given
        val eventLog = EventLog(
            name = "Package received",
            date = ZonedDateTime.parse("2023-09-28T10:15:30Z")
        )

        // When
        val uiModel = eventLog.toUiModel()

        // Then
        assertEquals("Package received", uiModel.name)
        assertEquals(ZonedDateTime.parse("2023-09-28T10:15:30Z"), uiModel.date)
    }

    @Test
    fun `should handle empty name in EventLog model`() {
        // Given
        val eventLog = EventLog(
            name = "",
            date = ZonedDateTime.parse("2023-09-28T10:15:30Z")
        )

        // When
        val uiModel = eventLog.toUiModel()

        // Then
        assertEquals("", uiModel.name)
        assertEquals(ZonedDateTime.parse("2023-09-28T10:15:30Z"), uiModel.date)
    }

    @Test
    fun `should handle different date formats`() {
        // Given
        val eventLog = EventLog(
            name = "Package updated",
            date = ZonedDateTime.parse("2024-01-01T00:00:00Z")
        )

        // When
        val uiModel = eventLog.toUiModel()

        // Then
        assertEquals("Package updated", uiModel.name)
        assertEquals(ZonedDateTime.parse("2024-01-01T00:00:00Z"), uiModel.date)
    }
}
