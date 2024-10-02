package pl.inpost.data.mapper

import org.junit.Assert.assertEquals
import org.junit.Test
import org.threeten.bp.ZonedDateTime
import pl.inpost.data.model.EventLogDto

class EventLogDtoMapperTest {

    @Test
    fun `should map EventLogDto to EventLog correctly`() {
        // Given
        val eventLogDto = EventLogDto(
            name = "Shipment created",
            date = ZonedDateTime.parse("2023-09-28T10:15:30Z")
        )

        // When
        val domainModel = eventLogDto.toDomain()

        // Then
        assertEquals("Shipment created", domainModel.name)
        assertEquals(ZonedDateTime.parse("2023-09-28T10:15:30Z"), domainModel.date)
    }

    @Test
    fun `should map EventLogDto with empty name`() {
        // Given
        val eventLogDto = EventLogDto(
            name = "",
            date = ZonedDateTime.parse("2023-09-28T10:15:30Z")
        )

        // When
        val domainModel = eventLogDto.toDomain()

        // Then
        assertEquals("", domainModel.name)
        assertEquals(ZonedDateTime.parse("2023-09-28T10:15:30Z"), domainModel.date)
    }

    @Test
    fun `should map EventLogDto with different date formats`() {
        // Given
        val eventLogDto = EventLogDto(
            name = "Shipment updated",
            date = ZonedDateTime.parse("2024-01-01T00:00:00Z")
        )

        // When
        val domainModel = eventLogDto.toDomain()

        // Then
        assertEquals("Shipment updated", domainModel.name)
        assertEquals(ZonedDateTime.parse("2024-01-01T00:00:00Z"), domainModel.date)
    }
}
