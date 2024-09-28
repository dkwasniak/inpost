package pl.inpost.recruitmenttask.mapper

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.inpost.domain.model.EventLog
import pl.inpost.domain.model.Operations
import pl.inpost.domain.model.Shipment
import pl.inpost.domain.model.Shipments
import java.time.ZonedDateTime

class ShipmentsUiMapperTest {

    @Test
    fun `should map Shipments to ShipmentsUiModel correctly`() {
        // Given
        val eventLog = EventLog(
            name = "Shipment created",
            date = ZonedDateTime.parse("2023-09-28T10:15:30Z")
        )

        val shipment = Shipment(
            number = "12345",
            shipmentType = "PACKAGE",
            status = "DELIVERED",
            eventLog = listOf(eventLog),
            openCode = "0000",
            expiryDate = ZonedDateTime.parse("2023-10-01T10:15:30Z"),
            storedDate = ZonedDateTime.parse("2023-09-27T10:15:30Z"),
            pickUpDate = ZonedDateTime.parse("2023-09-30T10:15:30Z"),
            receiver = null,
            sender = null,
            operations = Operations(
                manualArchive = true,
                delete = false,
                collect = true,
                highlight = false,
                expandAvizo = true,
                endOfWeekCollection = false
            )
        )

        val shipments = Shipments(
            shipments = listOf(shipment)
        )

        // When
        val uiModel = shipments.toUiModel()

        // Then
        assertEquals(1, uiModel.shipments.size)
        assertEquals("12345", uiModel.shipments[0].number)
        assertEquals("PACKAGE", uiModel.shipments[0].shipmentType)
        assertEquals("DELIVERED", uiModel.shipments[0].status)
        assertEquals("0000", uiModel.shipments[0].openCode)
        assertEquals(ZonedDateTime.parse("2023-10-01T10:15:30Z"), uiModel.shipments[0].expiryDate)
        assertEquals(ZonedDateTime.parse("2023-09-27T10:15:30Z"), uiModel.shipments[0].storedDate)
        assertEquals(ZonedDateTime.parse("2023-09-30T10:15:30Z"), uiModel.shipments[0].pickUpDate)

        // Nested EventLog model
        assertEquals(1, uiModel.shipments[0].eventLogUiModel.size)
        assertEquals("Shipment created", uiModel.shipments[0].eventLogUiModel[0].name)
        assertEquals(ZonedDateTime.parse("2023-09-28T10:15:30Z"), uiModel.shipments[0].eventLogUiModel[0].date)
    }

    @Test
    fun `should handle empty shipments list`() {
        // Given
        val shipments = Shipments(
            shipments = emptyList()
        )

        // When
        val uiModel = shipments.toUiModel()

        // Then
        assertEquals(0, uiModel.shipments.size)
    }

    @Test
    fun `should handle multiple shipments correctly`() {
        // Given
        val eventLog1 = EventLog(
            name = "Shipment created",
            date = ZonedDateTime.parse("2023-09-28T10:15:30Z")
        )

        val eventLog2 = EventLog(
            name = "Shipment delivered",
            date = ZonedDateTime.parse("2023-10-01T10:15:30Z")
        )

        val shipment1 = Shipment(
            number = "12345",
            shipmentType = "PACKAGE",
            status = "DELIVERED",
            eventLog = listOf(eventLog1),
            openCode = "0000",
            expiryDate = ZonedDateTime.parse("2023-10-01T10:15:30Z"),
            storedDate = ZonedDateTime.parse("2023-09-27T10:15:30Z"),
            pickUpDate = ZonedDateTime.parse("2023-09-30T10:15:30Z"),
            receiver = null,
            sender = null,
            operations = Operations(
                manualArchive = true,
                delete = false,
                collect = true,
                highlight = false,
                expandAvizo = true,
                endOfWeekCollection = false
            )
        )

        val shipment2 = Shipment(
            number = "67890",
            shipmentType = "DOCUMENT",
            status = "READY_TO_PICKUP",
            eventLog = listOf(eventLog2),
            openCode = "9999",
            expiryDate = ZonedDateTime.parse("2023-10-05T10:15:30Z"),
            storedDate = ZonedDateTime.parse("2023-09-29T10:15:30Z"),
            pickUpDate = ZonedDateTime.parse("2023-10-03T10:15:30Z"),
            receiver = null,
            sender = null,
            operations = Operations(
                manualArchive = false,
                delete = true,
                collect = false,
                highlight = true,
                expandAvizo = false,
                endOfWeekCollection = true
            )
        )

        val shipments = Shipments(
            shipments = listOf(shipment1, shipment2)
        )

        // When
        val uiModel = shipments.toUiModel()

        // Then
        assertEquals(2, uiModel.shipments.size)

        // Shipment 1
        assertEquals("12345", uiModel.shipments[0].number)
        assertEquals("PACKAGE", uiModel.shipments[0].shipmentType)
        assertEquals("DELIVERED", uiModel.shipments[0].status)
        assertEquals("0000", uiModel.shipments[0].openCode)
        assertEquals(1, uiModel.shipments[0].eventLogUiModel.size)
        assertEquals("Shipment created", uiModel.shipments[0].eventLogUiModel[0].name)

        // Shipment 2
        assertEquals("67890", uiModel.shipments[1].number)
        assertEquals("DOCUMENT", uiModel.shipments[1].shipmentType)
        assertEquals("READY_TO_PICKUP", uiModel.shipments[1].status)
        assertEquals("9999", uiModel.shipments[1].openCode)
        assertEquals(1, uiModel.shipments[1].eventLogUiModel.size)
        assertEquals("Shipment delivered", uiModel.shipments[1].eventLogUiModel[0].name)
    }
}
