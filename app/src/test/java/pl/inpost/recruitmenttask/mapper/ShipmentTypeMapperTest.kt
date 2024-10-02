package pl.inpost.recruitmenttask.mapper

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import pl.inpost.domain.model.ShipmentType
import pl.inpost.recruitmenttask.model.ShipmentTypeUiModel

class ShipmentTypeToUiModelTest {

    @Test
    fun `ShipmentType_PARCEL_LOCKER maps correctly to ShipmentTypeUiModel_PARCEL_LOCKER`() {
        // Given a ShipmentType.PARCEL_LOCKER
        val domainType = ShipmentType.PARCEL_LOCKER

        // When converting to UI model type
        val uiModelType = domainType.toUiModel()

        // Then the result should be ShipmentTypeUiModel.PARCEL_LOCKER
        assertEquals(ShipmentTypeUiModel.PARCEL_LOCKER, uiModelType)
    }

    @Test
    fun `ShipmentType_COURIER maps correctly to ShipmentTypeUiModel_COURIER`() {
        // Given a ShipmentType.COURIER
        val domainType = ShipmentType.COURIER

        // When converting to UI model type
        val uiModelType = domainType.toUiModel()

        // Then the result should be ShipmentTypeUiModel.COURIER
        assertEquals(ShipmentTypeUiModel.COURIER, uiModelType)
    }
}