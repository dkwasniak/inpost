import org.junit.Assert.assertEquals
import org.junit.Test
import org.threeten.bp.ZonedDateTime
import pl.inpost.domain.comparator.ShipmentComparator
import pl.inpost.domain.model.Customer
import pl.inpost.domain.model.EventLog
import pl.inpost.domain.model.Operations
import pl.inpost.domain.model.Shipment
import pl.inpost.domain.model.ShipmentStatus
import pl.inpost.domain.model.ShipmentType

class ShipmentComparatorTest {

    private val defaultEventLog = listOf(EventLog("Created", ZonedDateTime.now()))
    private val defaultOperations = Operations(
        manualArchive = false,
        delete = false,
        collect = true,
        highlight = false,
        expandAvizo = false,
        endOfWeekCollection = false
    )

    private val defaultReceiver = Customer("John Doe", "john@example.com", "123456789")
    private val defaultSender = Customer("Jane Smith", "jane@example.com", "987654321")

    @Test
    fun `should sort shipments correctly by status, dates, and number`() {
        // Given: A list of shipments with various status values and dates
        val shipments = listOf(
            Shipment(
                number = "5",
                shipmentType = ShipmentType.PARCEL_LOCKER,
                status = ShipmentStatus.OUT_FOR_DELIVERY, // Status: 11
                eventLog = defaultEventLog,
                openCode = "5555",
                expiryDate = null,
                storedDate = null,
                pickUpDate = null,
                receiver = defaultReceiver,
                sender = defaultSender,
                operations = defaultOperations
            ),
            Shipment(
                number = "1",
                shipmentType = ShipmentType.COURIER,
                status = ShipmentStatus.NOT_READY, // Status: 0 - lowest priority
                eventLog = defaultEventLog,
                openCode = null,
                expiryDate = null,
                storedDate = null,
                pickUpDate = null,
                receiver = defaultReceiver,
                sender = defaultSender,
                operations = defaultOperations
            ),
            Shipment(
                number = "3",
                shipmentType = ShipmentType.PARCEL_LOCKER,
                status = ShipmentStatus.READY_TO_PICKUP, // Status: 12 - highest priority
                eventLog = defaultEventLog,
                openCode = "3333",
                expiryDate = null,
                storedDate = null,
                pickUpDate = ZonedDateTime.parse("2023-11-15T10:00:00Z"),
                receiver = defaultReceiver,
                sender = defaultSender,
                operations = defaultOperations
            ),
            Shipment(
                number = "2",
                shipmentType = ShipmentType.COURIER,
                status = ShipmentStatus.CONFIRMED, // Status: 3
                eventLog = defaultEventLog,
                openCode = "2222",
                expiryDate = null,
                storedDate = null,
                pickUpDate = ZonedDateTime.parse("2023-10-01T10:00:00Z"),
                receiver = defaultReceiver,
                sender = defaultSender,
                operations = defaultOperations
            ),
            Shipment(
                number = "6",
                shipmentType = ShipmentType.PARCEL_LOCKER,
                status = ShipmentStatus.READY_TO_PICKUP, // Status: 12, earlier pickUpDate than 3
                eventLog = defaultEventLog,
                openCode = "6666",
                expiryDate = null,
                storedDate = null,
                pickUpDate = ZonedDateTime.parse("2023-11-10T10:00:00Z"),
                receiver = defaultReceiver,
                sender = defaultSender,
                operations = defaultOperations
            ),
            Shipment(
                number = "4",
                shipmentType = ShipmentType.COURIER,
                status = ShipmentStatus.CONFIRMED, // Status: 3, earlier pickUpDate than 2
                eventLog = defaultEventLog,
                openCode = "4444",
                expiryDate = null,
                storedDate = null,
                pickUpDate = ZonedDateTime.parse("2023-10-01T09:00:00Z"),
                receiver = defaultReceiver,
                sender = defaultSender,
                operations = defaultOperations
            ),
            Shipment(
                number = "7",
                shipmentType = ShipmentType.PARCEL_LOCKER,
                status = ShipmentStatus.DELIVERED, // Status: 8
                eventLog = defaultEventLog,
                openCode = "7777",
                expiryDate = null,
                storedDate = null,
                pickUpDate = null,
                receiver = defaultReceiver,
                sender = defaultSender,
                operations = defaultOperations
            ),
            Shipment(
                number = "8",
                shipmentType = ShipmentType.COURIER,
                status = ShipmentStatus.READY_TO_PICKUP, // Status: 12, null pickUpDate (should be last among READY_TO_PICKUP)
                eventLog = defaultEventLog,
                openCode = null,
                expiryDate = null,
                storedDate = null,
                pickUpDate = null,
                receiver = defaultReceiver,
                sender = defaultSender,
                operations = defaultOperations
            )
        )

        // When: Sorting shipments
        val sortedShipments = shipments.sortedWith(ShipmentComparator())

        // Debug output for sorted shipments
        println("Sorted Shipments: ${sortedShipments.map { it.number }}")

        // Then: Verify that the sorted order is correct
        assertEquals("6", sortedShipments[0].number) // READY_TO_PICKUP with earliest pickUpDate (2023-11-15T10:00:00Z)
        assertEquals("3", sortedShipments[1].number) // READY_TO_PICKUP with earlier pickUpDate (2023-11-10T10:00:00Z)
        assertEquals("8", sortedShipments[2].number) // READY_TO_PICKUP with null pickUpDate (sorted last among READY_TO_PICKUP)
        assertEquals("5", sortedShipments[3].number) // OUT_FOR_DELIVERY (Status: 11)
        assertEquals("7", sortedShipments[4].number) // DELIVERED (Status: 8)
        assertEquals("4", sortedShipments[5].number) // CONFIRMED with earliest pickUpDate (2023-10-01T09:00:00Z)
        assertEquals("2", sortedShipments[6].number) // CONFIRMED with later pickUpDate (2023-10-01T10:00:00Z)
        assertEquals("1", sortedShipments[7].number) // NOT_READY (Status: 0 - lowest priority)
    }
}

