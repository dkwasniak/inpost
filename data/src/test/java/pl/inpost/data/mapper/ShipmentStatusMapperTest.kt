package pl.inpost.data.mapper

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.inpost.domain.model.ShipmentStatus
import pl.inpost.data.model.ShipmentStatusDto

class ShipmentStatusDtoMapperTest {

    @Test
    fun `should map ADOPTED_AT_SORTING_CENTER Dto to Domain correctly`() {
        // Given
        val shipmentStatusDto = ShipmentStatusDto.ADOPTED_AT_SORTING_CENTER

        // When
        val domainModel = shipmentStatusDto.toDomain()

        // Then
        assertEquals(ShipmentStatus.ADOPTED_AT_SORTING_CENTER, domainModel)
    }

    @Test
    fun `should map SENT_FROM_SORTING_CENTER Dto to Domain correctly`() {
        // Given
        val shipmentStatusDto = ShipmentStatusDto.SENT_FROM_SORTING_CENTER

        // When
        val domainModel = shipmentStatusDto.toDomain()

        // Then
        assertEquals(ShipmentStatus.SENT_FROM_SORTING_CENTER, domainModel)
    }

    @Test
    fun `should map ADOPTED_AT_SOURCE_BRANCH Dto to Domain correctly`() {
        // Given
        val shipmentStatusDto = ShipmentStatusDto.ADOPTED_AT_SOURCE_BRANCH

        // When
        val domainModel = shipmentStatusDto.toDomain()

        // Then
        assertEquals(ShipmentStatus.ADOPTED_AT_SOURCE_BRANCH, domainModel)
    }

    @Test
    fun `should map SENT_FROM_SOURCE_BRANCH Dto to Domain correctly`() {
        // Given
        val shipmentStatusDto = ShipmentStatusDto.SENT_FROM_SOURCE_BRANCH

        // When
        val domainModel = shipmentStatusDto.toDomain()

        // Then
        assertEquals(ShipmentStatus.SENT_FROM_SOURCE_BRANCH, domainModel)
    }

    @Test
    fun `should map AVIZO Dto to Domain correctly`() {
        // Given
        val shipmentStatusDto = ShipmentStatusDto.AVIZO

        // When
        val domainModel = shipmentStatusDto.toDomain()

        // Then
        assertEquals(ShipmentStatus.AVIZO, domainModel)
    }

    @Test
    fun `should map CONFIRMED Dto to Domain correctly`() {
        // Given
        val shipmentStatusDto = ShipmentStatusDto.CONFIRMED

        // When
        val domainModel = shipmentStatusDto.toDomain()

        // Then
        assertEquals(ShipmentStatus.CONFIRMED, domainModel)
    }

    @Test
    fun `should map CREATED Dto to Domain correctly`() {
        // Given
        val shipmentStatusDto = ShipmentStatusDto.CREATED

        // When
        val domainModel = shipmentStatusDto.toDomain()

        // Then
        assertEquals(ShipmentStatus.CREATED, domainModel)
    }

    @Test
    fun `should map DELIVERED Dto to Domain correctly`() {
        // Given
        val shipmentStatusDto = ShipmentStatusDto.DELIVERED

        // When
        val domainModel = shipmentStatusDto.toDomain()

        // Then
        assertEquals(ShipmentStatus.DELIVERED, domainModel)
    }

    @Test
    fun `should map OTHER Dto to Domain correctly`() {
        // Given
        val shipmentStatusDto = ShipmentStatusDto.OTHER

        // When
        val domainModel = shipmentStatusDto.toDomain()

        // Then
        assertEquals(ShipmentStatus.OTHER, domainModel)
    }

    @Test
    fun `should map OUT_FOR_DELIVERY Dto to Domain correctly`() {
        // Given
        val shipmentStatusDto = ShipmentStatusDto.OUT_FOR_DELIVERY

        // When
        val domainModel = shipmentStatusDto.toDomain()

        // Then
        assertEquals(ShipmentStatus.OUT_FOR_DELIVERY, domainModel)
    }

    @Test
    fun `should map PICKUP_TIME_EXPIRED Dto to Domain correctly`() {
        // Given
        val shipmentStatusDto = ShipmentStatusDto.PICKUP_TIME_EXPIRED

        // When
        val domainModel = shipmentStatusDto.toDomain()

        // Then
        assertEquals(ShipmentStatus.PICKUP_TIME_EXPIRED, domainModel)
    }

    @Test
    fun `should map READY_TO_PICKUP Dto to Domain correctly`() {
        // Given
        val shipmentStatusDto = ShipmentStatusDto.READY_TO_PICKUP

        // When
        val domainModel = shipmentStatusDto.toDomain()

        // Then
        assertEquals(ShipmentStatus.READY_TO_PICKUP, domainModel)
    }

    @Test
    fun `should map RETURNED_TO_SENDER Dto to Domain correctly`() {
        // Given
        val shipmentStatusDto = ShipmentStatusDto.RETURNED_TO_SENDER

        // When
        val domainModel = shipmentStatusDto.toDomain()

        // Then
        assertEquals(ShipmentStatus.RETURNED_TO_SENDER, domainModel)
    }
}
