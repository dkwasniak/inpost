package pl.inpost.data.repository

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.ZonedDateTime
import pl.inpost.data.model.EventLogDto
import pl.inpost.data.model.OperationsDto
import pl.inpost.data.model.ShipmentDto
import pl.inpost.data.network.api.shipment.ShipmentApi

class ShipmentRepositoryImplTest {

    private val shipmentApi = mockk<ShipmentApi>()
    private val shipmentRepository = ShipmentRepositoryImpl(shipmentApi)

    @Test
    fun `should return mapped Shipments from API`() = runBlocking {
        // Given
        val eventLogDto = EventLogDto(
            name = "Shipment created",
            date = ZonedDateTime.parse("2023-09-28T10:15:30Z")
        )

        val shipmentDto = ShipmentDto(
            number = "12345",
            shipmentType = "PACKAGE",
            status = "DELIVERED",
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

        // Mocking the API call
        coEvery { shipmentApi.getShipments() } returns listOf(shipmentDto)

        // When
        val shipments = shipmentRepository.getShipment().first()

        // Then
        assertEquals(1, shipments.size)
        assertEquals("12345", shipments[0].number)
        assertEquals("PACKAGE", shipments[0].shipmentType)
        assertEquals("DELIVERED", shipments[0].status)
        assertEquals("0000", shipments[0].openCode)
        assertEquals(ZonedDateTime.parse("2023-10-01T10:15:30Z"), shipments[0].expiryDate)
        assertEquals(ZonedDateTime.parse("2023-09-27T10:15:30Z"), shipments[0].storedDate)
        assertEquals(ZonedDateTime.parse("2023-09-30T10:15:30Z"), shipments[0].pickUpDate)
        assertEquals(1, shipments[0].eventLog.size)
        assertEquals("Shipment created", shipments[0].eventLog[0].name)
        assertEquals(ZonedDateTime.parse("2023-09-28T10:15:30Z"), shipments[0].eventLog[0].date)
    }

    @Test
    fun `should return empty list when API returns no shipments`() = runBlocking {
        // Given
        coEvery { shipmentApi.getShipments() } returns emptyList()

        // When
        val shipments = shipmentRepository.getShipment().first()

        // Then
        assertEquals(0, shipments.size)
    }
}
