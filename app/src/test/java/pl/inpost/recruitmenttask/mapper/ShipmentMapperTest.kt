package pl.inpost.recruitmenttask.mapper

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.threeten.bp.ZonedDateTime
import pl.inpost.domain.model.Customer
import pl.inpost.domain.model.EventLog
import pl.inpost.domain.model.Operations
import pl.inpost.domain.model.Shipment
import pl.inpost.domain.model.ShipmentStatus
import pl.inpost.domain.model.ShipmentType
import pl.inpost.recruitmenttask.extension.toDisplayString
import pl.inpost.recruitmenttask.model.DetailsStatusUiModel
import pl.inpost.recruitmenttask.model.ShipmentStatusUiModel
import pl.inpost.recruitmenttask.model.ShipmentTypeUiModel
import java.util.Locale

class ShipmentMapperTest {

    private lateinit var originalLocale: Locale

    @Before
    fun setUp() {
        originalLocale = Locale.getDefault()
        Locale.setDefault(Locale.ENGLISH)
    }

    @After
    fun tearDown() {
        Locale.setDefault(originalLocale)
    }

    @Test
    fun `should map Shipment to ShipmentUiModel correctly with English Locale`() {
        // Given a sample Shipment object
        val eventLog = listOf(
            EventLog(name = "Shipment created", date = ZonedDateTime.parse("2023-09-28T10:15:30Z")),
            EventLog(name = "Shipment delivered", date = ZonedDateTime.parse("2023-10-01T10:15:30Z"))
        )
        val shipment = Shipment(
            number = "12345",
            shipmentType = ShipmentType.PARCEL_LOCKER,
            status = ShipmentStatus.READY_TO_PICKUP,
            eventLog = eventLog,
            openCode = "0000",
            expiryDate = ZonedDateTime.parse("2023-10-01T10:15:30Z"),
            storedDate = ZonedDateTime.parse("2023-09-27T10:15:30Z"),
            pickUpDate = ZonedDateTime.parse("2023-09-30T10:15:30Z"),
            receiver = Customer(name = "John Doe", email = "john.doe@example.com", phoneNumber = "123"),
            sender = Customer(name = "Jane Smith", email = "jane.smith@example.com", phoneNumber = "123"),
            operations = Operations(
                manualArchive = true,
                delete = false,
                collect = true,
                highlight = false,
                expandAvizo = true,
                endOfWeekCollection = false
            )
        )

        // When mapping to ShipmentUiModel
        val uiModel = shipment.toUiModel()

        // Then validate that the mapped fields are correct
        assertEquals("12345", uiModel.number)
        assertEquals(ShipmentTypeUiModel.PARCEL_LOCKER, uiModel.shipmentType)
        assertEquals(ShipmentStatusUiModel.READY_TO_PICKUP, uiModel.status)
        assertEquals(2, uiModel.eventLogUiModel.size)
        assertEquals("Shipment created", uiModel.eventLogUiModel[0].name)
        assertEquals(ZonedDateTime.parse("2023-09-28T10:15:30Z"), uiModel.eventLogUiModel[0].date)
        assertEquals("0000", uiModel.openCode)

        // Use the updated `toDisplayString` with a fixed Locale (English)
        val expectedExpiryDate = ZonedDateTime.parse("2023-10-01T10:15:30Z").toDisplayString()

        assertEquals(ZonedDateTime.parse("2023-09-27T10:15:30Z"), uiModel.storedDate)
        assertEquals(ZonedDateTime.parse("2023-09-30T10:15:30Z"), uiModel.pickUpDate)
        assertEquals("John Doe", uiModel.receiver?.name)
        assertEquals("Jane Smith", uiModel.sender?.name)

        // Verify operations are mapped correctly
        val operationsUiModel = uiModel.operations
        assertEquals(true, operationsUiModel.manualArchive)
        assertEquals(false, operationsUiModel.delete)
        assertEquals(true, operationsUiModel.collect)
        assertEquals(false, operationsUiModel.highlight)
        assertEquals(true, operationsUiModel.expandAvizo)
        assertEquals(false, operationsUiModel.endOfWeekCollection)

        // Validate the DetailsStatus mapping logic
        val detailsStatus = uiModel.detailsStatus
        assertEquals(DetailsStatusUiModel.AwaitingCollection(expectedExpiryDate!!), detailsStatus)
    }
}
