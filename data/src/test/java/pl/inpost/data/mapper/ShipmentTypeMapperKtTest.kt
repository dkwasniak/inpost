package pl.inpost.data.mapper

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import pl.inpost.data.model.ShipmentTypeDto
import pl.inpost.domain.model.ShipmentType

class ShipmentTypeMapperTest {

    @Test
    fun `ShipmentTypeDto_PARCEL_LOCKER maps correctly to ShipmentType_PARCEL_LOCKER`() {
        // Given
        val dtoType = ShipmentTypeDto.PARCEL_LOCKER

        // When
        val domainType = dtoType.toDomain()

        // Then
        assertEquals(ShipmentType.PARCEL_LOCKER, domainType)
    }

    @Test
    fun `ShipmentTypeDto_COURIER maps correctly to ShipmentType_COURIER`() {
        // Given
        val dtoType = ShipmentTypeDto.COURIER

        // When
        val domainType = dtoType.toDomain()

        // Then 
        assertEquals(ShipmentType.COURIER, domainType)
    }
}