package pl.inpost.data.mapper

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.inpost.data.model.CustomerDto
import pl.inpost.data.model.EventLogDto
import pl.inpost.data.model.OperationsDto
import pl.inpost.data.model.ShipmentDto
import pl.inpost.data.model.ShipmentStatusDto
import pl.inpost.data.model.ShipmentTypeDto
import pl.inpost.domain.model.ShipmentStatus
import pl.inpost.domain.model.ShipmentType
import java.time.ZonedDateTime

class ShipmentDtoMapperTest {

    @Test
    fun `should map ShipmentDto to Shipment correctly`() {
        // Given
        val eventLogDto = EventLogDto(
            name = "Shipment created",
            date = ZonedDateTime.parse("2023-09-28T10:15:30Z")
        )

        val receiverDto = CustomerDto(
            email = "receiver@example.com",
            phoneNumber = "123456789",
            name = "Receiver Name"
        )

        val senderDto = CustomerDto(
            email = "sender@example.com",
            phoneNumber = "987654321",
            name = "Sender Name"
        )

        val operationsDto = OperationsDto(
            manualArchive = true,
            delete = false,
            collect = true,
            highlight = false,
            expandAvizo = true,
            endOfWeekCollection = false
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
            receiver = receiverDto,
            sender = senderDto,
            operations = operationsDto
        )

        // When
        val domainModel = shipmentDto.toDomain()

        // Then
        assertEquals("12345", domainModel.number)
        assertEquals(ShipmentType.PARCEL_LOCKER, domainModel.shipmentType)
        assertEquals(ShipmentStatus.DELIVERED, domainModel.status)
        assertEquals("0000", domainModel.openCode)
        assertEquals(ZonedDateTime.parse("2023-10-01T10:15:30Z"), domainModel.expiryDate)
        assertEquals(ZonedDateTime.parse("2023-09-27T10:15:30Z"), domainModel.storedDate)
        assertEquals(ZonedDateTime.parse("2023-09-30T10:15:30Z"), domainModel.pickUpDate)

        // Nested models
        assertEquals(1, domainModel.eventLog.size)
        assertEquals("Shipment created", domainModel.eventLog[0].name)
        assertEquals(ZonedDateTime.parse("2023-09-28T10:15:30Z"), domainModel.eventLog[0].date)

        assertEquals("receiver@example.com", domainModel.receiver?.email)
        assertEquals("123456789", domainModel.receiver?.phoneNumber)
        assertEquals("Receiver Name", domainModel.receiver?.name)

        assertEquals("sender@example.com", domainModel.sender?.email)
        assertEquals("987654321", domainModel.sender?.phoneNumber)
        assertEquals("Sender Name", domainModel.sender?.name)

        assertEquals(true, domainModel.operations.manualArchive)
        assertEquals(false, domainModel.operations.delete)
        assertEquals(true, domainModel.operations.collect)
        assertEquals(false, domainModel.operations.highlight)
        assertEquals(true, domainModel.operations.expandAvizo)
        assertEquals(false, domainModel.operations.endOfWeekCollection)
    }

    @Test
    fun `should handle null receiver and sender in ShipmentDto`() {
        // Given
        val eventLogDto = EventLogDto(
            name = "Shipment updated",
            date = ZonedDateTime.parse("2023-09-28T10:15:30Z")
        )

        val operationsDto = OperationsDto(
            manualArchive = true,
            delete = false,
            collect = true,
            highlight = false,
            expandAvizo = true,
            endOfWeekCollection = false
        )

        val shipmentDto = ShipmentDto(
            number = "12345",
            shipmentType = ShipmentTypeDto.COURIER,
            status = ShipmentStatusDto.DELIVERED,
            eventLog = listOf(eventLogDto),
            openCode = "0000",
            expiryDate = null,
            storedDate = null,
            pickUpDate = null,
            receiver = null,
            sender = null,
            operations = operationsDto
        )

        // When
        val domainModel = shipmentDto.toDomain()

        // Then
        assertEquals("12345", domainModel.number)
        assertEquals(ShipmentType.COURIER, domainModel.shipmentType)
        assertEquals(ShipmentStatus.DELIVERED, domainModel.status)
        assertEquals("0000", domainModel.openCode)
        assertEquals(null, domainModel.expiryDate)
        assertEquals(null, domainModel.storedDate)
        assertEquals(null, domainModel.pickUpDate)

        // Nested models with null receiver and sender
        assertEquals(null, domainModel.receiver)
        assertEquals(null, domainModel.sender)

        // Nested event log
        assertEquals(1, domainModel.eventLog.size)
        assertEquals("Shipment updated", domainModel.eventLog[0].name)
        assertEquals(ZonedDateTime.parse("2023-09-28T10:15:30Z"), domainModel.eventLog[0].date)
    }

    @Test
    fun `should handle empty event log in ShipmentDto`() {
        // Given
        val operationsDto = OperationsDto(
            manualArchive = true,
            delete = false,
            collect = true,
            highlight = false,
            expandAvizo = true,
            endOfWeekCollection = false
        )

        val shipmentDto = ShipmentDto(
            number = "12345",
            shipmentType = ShipmentTypeDto.PARCEL_LOCKER,
            status = ShipmentStatusDto.DELIVERED,
            eventLog = emptyList(),
            openCode = "0000",
            expiryDate = null,
            storedDate = null,
            pickUpDate = null,
            receiver = null,
            sender = null,
            operations = operationsDto
        )

        // When
        val domainModel = shipmentDto.toDomain()

        // Then
        assertEquals("12345", domainModel.number)
        assertEquals(ShipmentType.PARCEL_LOCKER, domainModel.shipmentType)
        assertEquals(ShipmentStatus.DELIVERED, domainModel.status)
        assertEquals("0000", domainModel.openCode)
        assertEquals(0, domainModel.eventLog.size)
        assertEquals(null, domainModel.expiryDate)
        assertEquals(null, domainModel.storedDate)
        assertEquals(null, domainModel.pickUpDate)
    }
}
