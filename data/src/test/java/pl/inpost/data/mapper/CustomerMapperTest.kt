package pl.inpost.data.mapper

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.inpost.data.model.CustomerDto

class CustomerDtoMapperTest {

    @Test
    fun `should map CustomerDto to Customer correctly`() {
        // Given
        val customerDto = CustomerDto(
            email = "test@example.com",
            phoneNumber = "123456789",
            name = "John Doe"
        )

        // When
        val domainModel = customerDto.toDomain()

        // Then
        assertEquals("test@example.com", domainModel.email)
        assertEquals("123456789", domainModel.phoneNumber)
        assertEquals("John Doe", domainModel.name)
    }

    @Test
    fun `should map CustomerDto with null email`() {
        // Given
        val customerDto = CustomerDto(
            email = null,
            phoneNumber = "123456789",
            name = "John Doe"
        )

        // When
        val domainModel = customerDto.toDomain()

        // Then
        assertEquals(null, domainModel.email)
        assertEquals("123456789", domainModel.phoneNumber)
        assertEquals("John Doe", domainModel.name)
    }

    @Test
    fun `should map CustomerDto with null phoneNumber`() {
        // Given
        val customerDto = CustomerDto(
            email = "test@example.com",
            phoneNumber = null,
            name = "John Doe"
        )

        // When
        val domainModel = customerDto.toDomain()

        // Then
        assertEquals("test@example.com", domainModel.email)
        assertEquals(null, domainModel.phoneNumber)
        assertEquals("John Doe", domainModel.name)
    }

    @Test
    fun `should map CustomerDto with null name`() {
        // Given
        val customerDto = CustomerDto(
            email = "test@example.com",
            phoneNumber = "123456789",
            name = null
        )

        // When
        val domainModel = customerDto.toDomain()

        // Then
        assertEquals("test@example.com", domainModel.email)
        assertEquals("123456789", domainModel.phoneNumber)
        assertEquals(null, domainModel.name)
    }

    @Test
    fun `should map CustomerDto with all null fields`() {
        // Given
        val customerDto = CustomerDto(
            email = null,
            phoneNumber = null,
            name = null
        )

        // When
        val domainModel = customerDto.toDomain()

        // Then
        assertEquals(null, domainModel.email)
        assertEquals(null, domainModel.phoneNumber)
        assertEquals(null, domainModel.name)
    }
}
