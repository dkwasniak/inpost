package pl.inpost.recruitmenttask.mapper

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.inpost.domain.model.Operations

class OperationsUiMapperTest {

    @Test
    fun `should map Operations to OperationsUiModel correctly`() {
        // Given
        val operations = Operations(
            manualArchive = true,
            delete = false,
            collect = true,
            highlight = false,
            expandAvizo = true,
            endOfWeekCollection = false
        )

        // When
        val uiModel = operations.toUiModel()

        // Then
        assertEquals(true, uiModel.manualArchive)
        assertEquals(false, uiModel.delete)
        assertEquals(true, uiModel.collect)
        assertEquals(false, uiModel.highlight)
        assertEquals(true, uiModel.expandAvizo)
        assertEquals(false, uiModel.endOfWeekCollection)
    }

    @Test
    fun `should handle all true values in Operations model`() {
        // Given
        val operations = Operations(
            manualArchive = true,
            delete = true,
            collect = true,
            highlight = true,
            expandAvizo = true,
            endOfWeekCollection = true
        )

        // When
        val uiModel = operations.toUiModel()

        // Then
        assertEquals(true, uiModel.manualArchive)
        assertEquals(true, uiModel.delete)
        assertEquals(true, uiModel.collect)
        assertEquals(true, uiModel.highlight)
        assertEquals(true, uiModel.expandAvizo)
        assertEquals(true, uiModel.endOfWeekCollection)
    }

    @Test
    fun `should handle all false values in Operations model`() {
        // Given
        val operations = Operations(
            manualArchive = false,
            delete = false,
            collect = false,
            highlight = false,
            expandAvizo = false,
            endOfWeekCollection = false
        )

        // When
        val uiModel = operations.toUiModel()

        // Then
        assertEquals(false, uiModel.manualArchive)
        assertEquals(false, uiModel.delete)
        assertEquals(false, uiModel.collect)
        assertEquals(false, uiModel.highlight)
        assertEquals(false, uiModel.expandAvizo)
        assertEquals(false, uiModel.endOfWeekCollection)
    }
}
