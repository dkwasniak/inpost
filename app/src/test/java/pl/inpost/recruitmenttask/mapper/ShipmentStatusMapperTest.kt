package pl.inpost.recruitmenttask.mapper

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.inpost.domain.model.ShipmentStatus
import pl.inpost.recruitmenttask.model.ShipmentStatusUiModel

class ShipmentStatusUiMapperTest {

    @Test
    fun `should map ADOPTED_AT_SORTING_CENTER to UiModel correctly`() {
        // Given
        val shipmentStatus = ShipmentStatus.ADOPTED_AT_SORTING_CENTER

        // When
        val uiModel = shipmentStatus.toUiModel()

        // Then
        assertEquals(ShipmentStatusUiModel.ADOPTED_AT_SORTING_CENTER, uiModel)
    }

    @Test
    fun `should map SENT_FROM_SORTING_CENTER to UiModel correctly`() {
        // Given
        val shipmentStatus = ShipmentStatus.SENT_FROM_SORTING_CENTER

        // When
        val uiModel = shipmentStatus.toUiModel()

        // Then
        assertEquals(ShipmentStatusUiModel.SENT_FROM_SORTING_CENTER, uiModel)
    }

    @Test
    fun `should map ADOPTED_AT_SOURCE_BRANCH to UiModel correctly`() {
        // Given
        val shipmentStatus = ShipmentStatus.ADOPTED_AT_SOURCE_BRANCH

        // When
        val uiModel = shipmentStatus.toUiModel()

        // Then
        assertEquals(ShipmentStatusUiModel.ADOPTED_AT_SOURCE_BRANCH, uiModel)
    }

    @Test
    fun `should map SENT_FROM_SOURCE_BRANCH to UiModel correctly`() {
        // Given
        val shipmentStatus = ShipmentStatus.SENT_FROM_SOURCE_BRANCH

        // When
        val uiModel = shipmentStatus.toUiModel()

        // Then
        assertEquals(ShipmentStatusUiModel.SENT_FROM_SOURCE_BRANCH, uiModel)
    }

    @Test
    fun `should map AVIZO to UiModel correctly`() {
        // Given
        val shipmentStatus = ShipmentStatus.AVIZO

        // When
        val uiModel = shipmentStatus.toUiModel()

        // Then
        assertEquals(ShipmentStatusUiModel.AVIZO, uiModel)
    }

    @Test
    fun `should map CONFIRMED to UiModel correctly`() {
        // Given
        val shipmentStatus = ShipmentStatus.CONFIRMED

        // When
        val uiModel = shipmentStatus.toUiModel()

        // Then
        assertEquals(ShipmentStatusUiModel.CONFIRMED, uiModel)
    }

    @Test
    fun `should map CREATED to UiModel correctly`() {
        // Given
        val shipmentStatus = ShipmentStatus.CREATED

        // When
        val uiModel = shipmentStatus.toUiModel()

        // Then
        assertEquals(ShipmentStatusUiModel.CREATED, uiModel)
    }

    @Test
    fun `should map DELIVERED to UiModel correctly`() {
        // Given
        val shipmentStatus = ShipmentStatus.DELIVERED

        // When
        val uiModel = shipmentStatus.toUiModel()

        // Then
        assertEquals(ShipmentStatusUiModel.DELIVERED, uiModel)
    }

    @Test
    fun `should map OTHER to UiModel correctly`() {
        // Given
        val shipmentStatus = ShipmentStatus.OTHER

        // When
        val uiModel = shipmentStatus.toUiModel()

        // Then
        assertEquals(ShipmentStatusUiModel.OTHER, uiModel)
    }

    @Test
    fun `should map OUT_FOR_DELIVERY to UiModel correctly`() {
        // Given
        val shipmentStatus = ShipmentStatus.OUT_FOR_DELIVERY

        // When
        val uiModel = shipmentStatus.toUiModel()

        // Then
        assertEquals(ShipmentStatusUiModel.OUT_FOR_DELIVERY, uiModel)
    }

    @Test
    fun `should map PICKUP_TIME_EXPIRED to UiModel correctly`() {
        // Given
        val shipmentStatus = ShipmentStatus.PICKUP_TIME_EXPIRED

        // When
        val uiModel = shipmentStatus.toUiModel()

        // Then
        assertEquals(ShipmentStatusUiModel.PICKUP_TIME_EXPIRED, uiModel)
    }

    @Test
    fun `should map READY_TO_PICKUP to UiModel correctly`() {
        // Given
        val shipmentStatus = ShipmentStatus.READY_TO_PICKUP

        // When
        val uiModel = shipmentStatus.toUiModel()

        // Then
        assertEquals(ShipmentStatusUiModel.READY_TO_PICKUP, uiModel)
    }

    @Test
    fun `should map RETURNED_TO_SENDER to UiModel correctly`() {
        // Given
        val shipmentStatus = ShipmentStatus.RETURNED_TO_SENDER

        // When
        val uiModel = shipmentStatus.toUiModel()

        // Then
        assertEquals(ShipmentStatusUiModel.RETURNED_TO_SENDER, uiModel)
    }
}
