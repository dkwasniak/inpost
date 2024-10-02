import org.junit.Assert.assertEquals
import org.junit.Test
import pl.inpost.domain.model.Customer
import pl.inpost.recruitmenttask.mapper.toUiModel

class CustomerMapperTest {

    @Test
    fun `toUiModel should map name when name is not null`() {
        // Given
        val customer = Customer(name = "John Doe", email = "john.doe@example.com", phoneNumber = "123456789")

        // When
        val uiModel = customer.toUiModel()

        // Then
        assertEquals("John Doe", uiModel.name)
    }

    @Test
    fun `toUiModel should map email when name is null and email is not null`() {
        // Given
        val customer = Customer(name = null, email = "john.doe@example.com", phoneNumber = "123456789")

        // When
        val uiModel = customer.toUiModel()

        // Then
        assertEquals("john.doe@example.com", uiModel.name)
    }

    @Test
    fun `toUiModel should map phoneNumber when both name and email are null`() {
        // Given
        val customer = Customer(name = null, email = null, phoneNumber = "123456789")

        // When
        val uiModel = customer.toUiModel()

        // Then
        assertEquals("123456789", uiModel.name)
    }

    @Test
    fun `toUiModel should map empty string when all fields are null`() {
        // Given
        val customer = Customer(name = null, email = null, phoneNumber = null)

        // When
        val uiModel = customer.toUiModel()

        // Then
        assertEquals(null, uiModel.name)
    }
}
