package pl.inpost.data.mapper

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.inpost.data.model.EventLogDto
import pl.inpost.data.model.OperationsDto
import pl.inpost.data.model.ShipmentDto
import pl.inpost.data.model.ShipmentStatusDto
import pl.inpost.data.model.ShipmentTypeDto
import pl.inpost.data.model.ShipmentsDto
import pl.inpost.domain.model.ShipmentStatus
import pl.inpost.domain.model.ShipmentType
import java.time.ZonedDateTime

class ShipmentsDtoMapperTest {

    @Test
    fun `should map ShipmentsDto to Shipments correctly`() {
        // Given
        val eventLogDto = EventLogDto(
            name = "Shipment created",
            date = ZonedDateTime.parse("2023-09-28T10:15:30Z")
        )

        val shipmentDto = ShipmentDto(
            number = "12345",
            shipmentType = ShipmentTypeDto.PARCEL_LOCKER,
            status = ShipmentStatusDto.DELIVERED,
            eventLog = listOf(eventLogDto),
            openCode = "0000",
            expiryDate = ZonedDateTime.parse("2023-10-01T10:15:30Z"),
            storedDate = ZonedDateTime.parse("2023-09-27T10:15:30Z"),
            pickUpDate = ZonedDateTime.parse("2023-09-30T10:15:30Z"),
            receiver = null,
            sender = null,
            operations = OperationsDto(
                manualArchive = true,
                delete = false,
                collect = true,
                highlight = false,
                expandAvizo = true,
                endOfWeekCollection = false
            )
        )

        val shipmentsDto = ShipmentsDto(
            shipments = listOf(shipmentDto)
        )

        // When
        val domainModel = shipmentsDto.toDomain()

        // Then
        assertEquals(1, domainModel.shipments.size)
        assertEquals("12345", domainModel.shipments[0].number)
        assertEquals(ShipmentType.PARCEL_LOCKER, domainModel.shipments[0].shipmentType)
        assertEquals(ShipmentStatus.DELIVERED, domainModel.shipments[0].status)
        assertEquals("0000", domainModel.shipments[0].openCode)
        assertEquals(ZonedDateTime.parse("2023-10-01T10:15:30Z"), domainModel.shipments[0].expiryDate)
        assertEquals(ZonedDateTime.parse("2023-09-27T10:15:30Z"), domainModel.shipments[0].storedDate)
        assertEquals(ZonedDateTime.parse("2023-09-30T10:15:30Z"), domainModel.shipments[0].pickUpDate)

        // Nested EventLog model
        assertEquals(1, domainModel.shipments[0].eventLog.size)
        assertEquals("Shipment created", domainModel.shipments[0].eventLog[0].name)
        assertEquals(ZonedDateTime.parse("2023-09-28T10:15:30Z"), domainModel.shipments[0].eventLog[0].date)
    }

    @Test
    fun `should handle empty shipments list`() {
        // Given
        val shipmentsDto = ShipmentsDto(
            shipments = emptyList()
        )

        // When
        val domainModel = shipmentsDto.toDomain()

        // Then
        assertEquals(0, domainModel.shipments.size)
    }

    @Test
    fun `should handle multiple shipments correctly`() {
        // Given
        val eventLogDto1 = EventLogDto(
            name = "Shipment created",
            date = ZonedDateTime.parse("2023-09-28T10:15:30Z")
        )

        val eventLogDto2 = EventLogDto(
            name = "Shipment delivered",
            date = ZonedDateTime.parse("2023-10-01T10:15:30Z")
        )

        val shipmentDto1 = ShipmentDto(
            number = "12345",
            shipmentType = ShipmentTypeDto.PARCEL_LOCKER,
            status = ShipmentStatusDto.DELIVERED,
            eventLog = listOf(eventLogDto1),
            openCode = "0000",
            expiryDate = ZonedDateTime.parse("2023-10-01T10:15:30Z"),
            storedDate = ZonedDateTime.parse("2023-09-27T10:15:30Z"),
            pickUpDate = ZonedDateTime.parse("2023-09-30T10:15:30Z"),
            receiver = null,
            sender = null,
            operations = OperationsDto(
                manualArchive = true,
                delete = false,
                collect = true,
                highlight = false,
                expandAvizo = true,
                endOfWeekCollection = false
            )
        )

        val shipmentDto2 = ShipmentDto(
            number = "67890",
            shipmentType = ShipmentTypeDto.COURIER,
            status = ShipmentStatusDto.READY_TO_PICKUP,
            eventLog = listOf(eventLogDto2),
            openCode = "9999",
            expiryDate = ZonedDateTime.parse("2023-10-05T10:15:30Z"),
            storedDate = ZonedDateTime.parse("2023-09-29T10:15:30Z"),
            pickUpDate = ZonedDateTime.parse("2023-10-03T10:15:30Z"),
            receiver = null,
            sender = null,
            operations = OperationsDto(
                manualArchive = false,
                delete = true,
                collect = false,
                highlight = true,
                expandAvizo = false,
                endOfWeekCollection = true
            )
        )

        val shipmentsDto = ShipmentsDto(
            shipments = listOf(shipmentDto1, shipmentDto2)
        )

        // When
        val domainModel = shipmentsDto.toDomain()

        // Then
        assertEquals(2, domainModel.shipments.size)

        // Shipment 1
        assertEquals("12345", domainModel.shipments[0].number)
        assertEquals(ShipmentType.PARCEL_LOCKER, domainModel.shipments[0].shipmentType)
        assertEquals(ShipmentStatus.DELIVERED, domainModel.shipments[0].status)
        assertEquals("0000", domainModel.shipments[0].openCode)
        assertEquals(1, domainModel.shipments[0].eventLog.size)
        assertEquals("Shipment created", domainModel.shipments[0].eventLog[0].name)

        // Shipment 2
        assertEquals("67890", domainModel.shipments[1].number)
        assertEquals(ShipmentType.COURIER, domainModel.shipments[1].shipmentType)
        assertEquals(ShipmentStatus.READY_TO_PICKUP, domainModel.shipments[1].status)
        assertEquals("9999", domainModel.shipments[1].openCode)
        assertEquals(1, domainModel.shipments[1].eventLog.size)
        assertEquals("Shipment delivered", domainModel.shipments[1].eventLog[0].name)
    }
}
