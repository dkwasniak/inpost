package pl.inpost.data.mapper

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.inpost.data.model.OperationsDto

class OperationsDtoMapperTest {

    @Test
    fun `should map OperationsDto to Operations correctly`() {
        // Given
        val operationsDto = OperationsDto(
            manualArchive = true,
            delete = false,
            collect = true,
            highlight = false,
            expandAvizo = true,
            endOfWeekCollection = false
        )

        // When
        val domainModel = operationsDto.toDomain()

        // Then
        assertEquals(true, domainModel.manualArchive)
        assertEquals(false, domainModel.delete)
        assertEquals(true, domainModel.collect)
        assertEquals(false, domainModel.highlight)
        assertEquals(true, domainModel.expandAvizo)
        assertEquals(false, domainModel.endOfWeekCollection)
    }

    @Test
    fun `should map OperationsDto with all true values`() {
        // Given
        val operationsDto = OperationsDto(
            manualArchive = true,
            delete = true,
            collect = true,
            highlight = true,
            expandAvizo = true,
            endOfWeekCollection = true
        )

        // When
        val domainModel = operationsDto.toDomain()

        // Then
        assertEquals(true, domainModel.manualArchive)
        assertEquals(true, domainModel.delete)
        assertEquals(true, domainModel.collect)
        assertEquals(true, domainModel.highlight)
        assertEquals(true, domainModel.expandAvizo)
        assertEquals(true, domainModel.endOfWeekCollection)
    }

    @Test
    fun `should map OperationsDto with all false values`() {
        // Given
        val operationsDto = OperationsDto(
            manualArchive = false,
            delete = false,
            collect = false,
            highlight = false,
            expandAvizo = false,
            endOfWeekCollection = false
        )

        // When
        val domainModel = operationsDto.toDomain()

        // Then
        assertEquals(false, domainModel.manualArchive)
        assertEquals(false, domainModel.delete)
        assertEquals(false, domainModel.collect)
        assertEquals(false, domainModel.highlight)
        assertEquals(false, domainModel.expandAvizo)
        assertEquals(false, domainModel.endOfWeekCollection)
    }

    @Test
    fun `should map OperationsDto with mixed values`() {
        // Given
        val operationsDto = OperationsDto(
            manualArchive = false,
            delete = true,
            collect = false,
            highlight = true,
            expandAvizo = false,
            endOfWeekCollection = true
        )

        // When
        val domainModel = operationsDto.toDomain()

        // Then
        assertEquals(false, domainModel.manualArchive)
        assertEquals(true, domainModel.delete)
        assertEquals(false, domainModel.collect)
        assertEquals(true, domainModel.highlight)
        assertEquals(false, domainModel.expandAvizo)
        assertEquals(true, domainModel.endOfWeekCollection)
    }
}
