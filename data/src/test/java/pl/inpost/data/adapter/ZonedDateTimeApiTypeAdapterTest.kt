package pl.inpost.data.adapter

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeParseException

class ZonedDateTimeApiTypeAdapterTest {

    private val adapter = ZonedDateTimeApiTypeAdapter()

    @Test
    fun `should parse ISO date string to ZonedDateTime correctly`() {
        // Given
        val dateString = "2022-11-29T04:56:07Z"

        // When
        val result = adapter.toZonedDateTime(dateString)

        // Then
        val expectedDate = ZonedDateTime.parse(dateString)
        assertEquals(expectedDate, result)
    }

    @Test
    fun `should format ZonedDateTime to ISO date string correctly`() {
        // Given
        val zonedDateTime = ZonedDateTime.parse("2022-11-29T04:56:07Z")

        // When
        val result = adapter.fromZonedDateTime(zonedDateTime)

        // Then
        val expectedString = "2022-11-29T04:56:07Z"
        assertEquals(expectedString, result)
    }

    @Test
    fun `should return null when formatting null ZonedDateTime`() {
        // Given
        val zonedDateTime: ZonedDateTime? = null

        // When
        val result = adapter.fromZonedDateTime(zonedDateTime)

        // Then
        assertNull(result)
    }

    @Test(expected = DateTimeParseException::class)
    fun `should throw DateTimeParseException for invalid date string`() {
        // Given
        val invalidDateString = "invalid-date"

        // When
        adapter.toZonedDateTime(invalidDateString)

        // Then
        // Expected exception: DateTimeParseException
    }
}
