package pl.inpost.recruitmenttask.mapper

import org.junit.Assert.assertEquals
import org.junit.Test
import org.threeten.bp.ZonedDateTime
import pl.inpost.domain.model.Customer
import pl.inpost.domain.model.EventLog
import pl.inpost.domain.model.Operations
import pl.inpost.domain.model.Shipment
import pl.inpost.domain.model.ShipmentStatus
import pl.inpost.domain.model.ShipmentType
import pl.inpost.recruitmenttask.model.ShipmentStatusUiModel
import pl.inpost.recruitmenttask.model.ShipmentTypeUiModel

class ShipmentUiMapperTest {

    @Test
    fun `should map Shipment to ShipmentUiModel correctly`() {
        // Given
        val eventLog = EventLog(
            name = "Shipment created",
            date = ZonedDateTime.parse("2023-09-28T10:15:30Z")
        )

        val receiver = Customer(
            email = "receiver@example.com",
            phoneNumber = "123456789",
            name = "Receiver Name"
        )

        val sender = Customer(
            email = "sender@example.com",
            phoneNumber = "987654321",
            name = "Sender Name"
        )

        val operations = Operations(
            manualArchive = true,
            delete = false,
            collect = true,
            highlight = false,
            expandAvizo = true,
            endOfWeekCollection = false
        )

        val shipment = Shipment(
            number = "12345",
            shipmentType = ShipmentType.PARCEL_LOCKER,
            status = ShipmentStatus.DELIVERED,
            eventLog = listOf(eventLog),
            openCode = "0000",
            expiryDate = ZonedDateTime.parse("2023-10-01T10:15:30Z"),
            storedDate = ZonedDateTime.parse("2023-09-27T10:15:30Z"),
            pickUpDate = ZonedDateTime.parse("2023-09-30T10:15:30Z"),
            receiver = receiver,
            sender = sender,
            operations = operations
        )

        // When
        val uiModel = shipment.toUiModel()

        // Then
        assertEquals("12345", uiModel.number)
        assertEquals(ShipmentTypeUiModel.PARCEL_LOCKER, uiModel.shipmentType)
        assertEquals(ShipmentStatusUiModel.DELIVERED, uiModel.status)
        assertEquals("0000", uiModel.openCode)
        assertEquals(ZonedDateTime.parse("2023-10-01T10:15:30Z"), uiModel.expiryDate)
        assertEquals(ZonedDateTime.parse("2023-09-27T10:15:30Z"), uiModel.storedDate)
        assertEquals(ZonedDateTime.parse("2023-09-30T10:15:30Z"), uiModel.pickUpDate)

        // Nested models
        assertEquals(1, uiModel.eventLogUiModel.size)
        assertEquals("Shipment created", uiModel.eventLogUiModel[0].name)
        assertEquals(ZonedDateTime.parse("2023-09-28T10:15:30Z"), uiModel.eventLogUiModel[0].date)

        assertEquals("Receiver Name", uiModel.receiver?.name)
        assertEquals("Sender Name", uiModel.sender?.name)

        assertEquals(true, uiModel.operationsUiModel.manualArchive)
        assertEquals(false, uiModel.operationsUiModel.delete)
        assertEquals(true, uiModel.operationsUiModel.collect)
        assertEquals(false, uiModel.operationsUiModel.highlight)
        assertEquals(true, uiModel.operationsUiModel.expandAvizo)
        assertEquals(false, uiModel.operationsUiModel.endOfWeekCollection)
    }

    @Test
    fun `should handle null receiver and sender in Shipment`() {
        // Given
        val eventLog = EventLog(
            name = "Shipment updated",
            date = ZonedDateTime.parse("2023-09-28T10:15:30Z")
        )

        val operations = Operations(
            manualArchive = true,
            delete = false,
            collect = true,
            highlight = false,
            expandAvizo = true,
            endOfWeekCollection = false
        )

        val shipment = Shipment(
            number = "12345",
            shipmentType = ShipmentType.PARCEL_LOCKER,
            status = ShipmentStatus.DELIVERED,
            eventLog = listOf(eventLog),
            openCode = "0000",
            expiryDate = null,
            storedDate = null,
            pickUpDate = null,
            receiver = null,
            sender = null,
            operations = operations
        )

        // When
        val uiModel = shipment.toUiModel()

        // Then
        assertEquals("12345", uiModel.number)
        assertEquals(ShipmentTypeUiModel.PARCEL_LOCKER, uiModel.shipmentType)
        assertEquals(ShipmentStatusUiModel.DELIVERED, uiModel.status)
        assertEquals("0000", uiModel.openCode)
        assertEquals(null, uiModel.expiryDate)
        assertEquals(null, uiModel.storedDate)
        assertEquals(null, uiModel.pickUpDate)

        // Nested models with null receiver and sender
        assertEquals(null, uiModel.receiver)
        assertEquals(null, uiModel.sender)

        // Nested event log
        assertEquals(1, uiModel.eventLogUiModel.size)
        assertEquals("Shipment updated", uiModel.eventLogUiModel[0].name)
        assertEquals(ZonedDateTime.parse("2023-09-28T10:15:30Z"), uiModel.eventLogUiModel[0].date)
    }

    @Test
    fun `should handle empty event log in Shipment`() {
        // Given
        val operations = Operations(
            manualArchive = true,
            delete = false,
            collect = true,
            highlight = false,
            expandAvizo = true,
            endOfWeekCollection = false
        )

        val shipment = Shipment(
            number = "12345",
            shipmentType = ShipmentType.PARCEL_LOCKER,
            status = ShipmentStatus.DELIVERED,
            eventLog = emptyList(),
            openCode = "0000",
            expiryDate = null,
            storedDate = null,
            pickUpDate = null,
            receiver = null,
            sender = null,
            operations = operations
        )

        // When
        val uiModel = shipment.toUiModel()

        // Then
        assertEquals("12345", uiModel.number)
        assertEquals(ShipmentTypeUiModel.PARCEL_LOCKER, uiModel.shipmentType)
        assertEquals(ShipmentStatusUiModel.DELIVERED, uiModel.status)
        assertEquals("0000", uiModel.openCode)
        assertEquals(0, uiModel.eventLogUiModel.size)
        assertEquals(null, uiModel.expiryDate)
        assertEquals(null, uiModel.storedDate)
        assertEquals(null, uiModel.pickUpDate)
    }
}
