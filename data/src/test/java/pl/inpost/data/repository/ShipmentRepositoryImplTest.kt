package pl.inpost.data.repository

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.threeten.bp.ZonedDateTime
import pl.inpost.data.model.EventLogDto
import pl.inpost.data.model.OperationsDto
import pl.inpost.data.model.ShipmentDto
import pl.inpost.data.model.ShipmentStatusDto
import pl.inpost.data.model.ShipmentTypeDto
import pl.inpost.data.network.api.shipment.ShipmentApi
import pl.inpost.domain.model.ShipmentStatus
import pl.inpost.domain.model.ShipmentType

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

        // Mocking the API call
        coEvery { shipmentApi.getShipments() } returns listOf(shipmentDto)

        // When
        val shipments = shipmentRepository.getShipment().first()

        // Then
        assertEquals(1, shipments.size)
        assertEquals("12345", shipments[0].number)
        assertEquals(ShipmentType.PARCEL_LOCKER, shipments[0].shipmentType)
        assertEquals(ShipmentStatus.DELIVERED, shipments[0].status)
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


    @Test
    fun `should filter out shipments with NOT_READY status`() = runBlocking {
        // Given
        val shipmentWithNotReadyStatus = ShipmentDto(
            number = "12345",
            shipmentType = ShipmentTypeDto.PARCEL_LOCKER,
            status = ShipmentStatusDto.NOT_READY, // This shipment should be filtered out
            eventLog = emptyList(),
            openCode = "0000",
            expiryDate = null,
            storedDate = null,
            pickUpDate = null,
            receiver = null,
            sender = null,
            operations = OperationsDto(

            )
        )

        val shipmentWithDeliveredStatus = ShipmentDto(
            number = "67890",
            shipmentType = ShipmentTypeDto.COURIER,
            status = ShipmentStatusDto.DELIVERED, // This shipment should be included
            eventLog = emptyList(),
            openCode = "1111",
            expiryDate = null,
            storedDate = null,
            pickUpDate = null,
            receiver = null,
            sender = null,
            operations = OperationsDto()
        )

        coEvery { shipmentApi.getShipments() } returns listOf(shipmentWithNotReadyStatus, shipmentWithDeliveredStatus)

        // When
        val shipments = shipmentRepository.getShipment().first()

        // Then
        assertEquals(1, shipments.size)
        assertEquals("67890", shipments[0].number)
        assertEquals(ShipmentStatus.DELIVERED, shipments[0].status)
    }

    @Test
    fun `should return only valid shipments when some are NOT_READY`() = runBlocking {
        // Given
        val shipment1 = ShipmentDto(
            number = "12345",
            shipmentType = ShipmentTypeDto.PARCEL_LOCKER,
            status = ShipmentStatusDto.NOT_READY, // This shipment should be filtered out
            eventLog = emptyList(),
            openCode = "0000",
            expiryDate = null,
            storedDate = null,
            pickUpDate = null,
            receiver = null,
            sender = null,
            operations = OperationsDto()
        )

        val shipment2 = ShipmentDto(
            number = "67890",
            shipmentType = ShipmentTypeDto.COURIER,
            status = ShipmentStatusDto.DELIVERED, // This shipment should be included
            eventLog = emptyList(),
            openCode = "1111",
            expiryDate = null,
            storedDate = null,
            pickUpDate = null,
            receiver = null,
            sender = null,
            operations = OperationsDto()
        )

        val shipment3 = ShipmentDto(
            number = "54321",
            shipmentType = ShipmentTypeDto.COURIER,
            status = ShipmentStatusDto.CONFIRMED, // This shipment should be included
            eventLog = emptyList(),
            openCode = "2222",
            expiryDate = null,
            storedDate = null,
            pickUpDate = null,
            receiver = null,
            sender = null,
            operations = OperationsDto()
        )

        coEvery { shipmentApi.getShipments() } returns listOf(shipment1, shipment2, shipment3)

        // When
        val shipments = shipmentRepository.getShipment().first()

        // Then
        assertEquals(2, shipments.size)
        assertEquals("67890", shipments[0].number)
        assertEquals(ShipmentStatus.DELIVERED, shipments[0].status)
        assertEquals("54321", shipments[1].number)
        assertEquals(ShipmentStatus.CONFIRMED, shipments[1].status)
    }
}
