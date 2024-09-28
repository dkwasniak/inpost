package pl.inpost.recruitmenttask.mapper

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.inpost.domain.model.Customer

class CustomerUiMapperTest {

    @Test
    fun `should map Customer to CustomerUiModel correctly`() {
        // Given
        val customer = Customer(
            email = "test@example.com",
            phoneNumber = "123456789",
            name = "John Doe"
        )

        // When
        val uiModel = customer.toUiModel()

        // Then
        assertEquals("test@example.com", uiModel.email)
        assertEquals("123456789", uiModel.phoneNumber)
        assertEquals("John Doe", uiModel.name)
    }

    @Test
    fun `should handle null email in Customer model`() {
        // Given
        val customer = Customer(
            email = null,
            phoneNumber = "123456789",
            name = "John Doe"
        )

        // When
        val uiModel = customer.toUiModel()

        // Then
        assertEquals(null, uiModel.email)
        assertEquals("123456789", uiModel.phoneNumber)
        assertEquals("John Doe", uiModel.name)
    }

    @Test
    fun `should handle null phoneNumber in Customer model`() {
        // Given
        val customer = Customer(
            email = "test@example.com",
            phoneNumber = null,
            name = "John Doe"
        )

        // When
        val uiModel = customer.toUiModel()

        // Then
        assertEquals("test@example.com", uiModel.email)
        assertEquals(null, uiModel.phoneNumber)
        assertEquals("John Doe", uiModel.name)
    }

    @Test
    fun `should handle null name in Customer model`() {
        // Given
        val customer = Customer(
            email = "test@example.com",
            phoneNumber = "123456789",
            name = null
        )

        // When
        val uiModel = customer.toUiModel()

        // Then
        assertEquals("test@example.com", uiModel.email)
        assertEquals("123456789", uiModel.phoneNumber)
        assertEquals(null, uiModel.name)
    }

    @Test
    fun `should handle completely null fields in Customer model`() {
        // Given
        val customer = Customer(
            email = null,
            phoneNumber = null,
            name = null
        )

        // When
        val uiModel = customer.toUiModel()

        // Then
        assertEquals(null, uiModel.email)
        assertEquals(null, uiModel.phoneNumber)
        assertEquals(null, uiModel.name)
    }
}
