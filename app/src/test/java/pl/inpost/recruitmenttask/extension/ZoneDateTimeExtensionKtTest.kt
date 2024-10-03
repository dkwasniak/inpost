package pl.inpost.recruitmenttask.extension

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.threeten.bp.ZonedDateTime
import java.util.Locale

class ZoneDateTimeExtensionKtTest {

    private lateinit var originalLocale: Locale

    @Before
    fun setUp() {
        originalLocale = Locale.getDefault()
    }

    @After
    fun tearDown() {
        Locale.setDefault(originalLocale)
    }

    @Test
    fun `should return null for null ZonedDateTime`() {
        // Given
        val dateTime: ZonedDateTime? = null

        // When
        val result = dateTime.toDisplayString()

        // Then
        assertEquals(null, result)
    }

    @Test
    fun `should format ZonedDateTime correctly for ENGLISH locale`() {
        // Given
        val dateTime = ZonedDateTime.parse("2023-10-01T10:15:30Z")

        Locale.setDefault(Locale.ENGLISH)

        // When
        val result = dateTime.toDisplayString()

        // Then
        assertEquals("Sun | 01.10.2023 | 10:15", result)
    }

    @Test
    fun `should format ZonedDateTime correctly for POLISH locale`() {
        // Given
        val dateTime = ZonedDateTime.parse("2023-10-01T10:15:30Z")

        Locale.setDefault(Locale("pl", "PL"))

        // When
        val result = dateTime.toDisplayString()

        // Then
        assertEquals("niedz. | 01.10.2023 | 10:15", result)
    }
}