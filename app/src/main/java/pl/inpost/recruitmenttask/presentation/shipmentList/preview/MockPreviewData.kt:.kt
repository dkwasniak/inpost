package pl.inpost.recruitmenttask.presentation.shipmentList.preview

import org.threeten.bp.ZonedDateTime
import pl.inpost.recruitmenttask.model.CustomerUiModel
import pl.inpost.recruitmenttask.model.EventLogUiModel
import pl.inpost.recruitmenttask.model.OperationsUiModel
import pl.inpost.recruitmenttask.model.ShipmentStatusUiModel
import pl.inpost.recruitmenttask.model.ShipmentTypeUiModel
import pl.inpost.recruitmenttask.model.ShipmentUiModel

val mockShipment1 = ShipmentUiModel(
    number = "235678654323567889762231",
    shipmentType = ShipmentTypeUiModel.PARCEL_LOCKER,
    status = ShipmentStatusUiModel.READY_TO_PICKUP,
    eventLogUiModel = listOf(
        EventLogUiModel(name = "Shipment registered", date = ZonedDateTime.now().minusDays(3)),
        EventLogUiModel(
            name = "Shipment ready for collection",
            date = ZonedDateTime.now().minusDays(1)
        )
    ),
    openCode = "ABC123",
    expiryDate = ZonedDateTime.now().plusDays(3),
    storedDate = ZonedDateTime.now().minusDays(1),
    pickUpDate = null,
    receiver = CustomerUiModel(name = "John Doe"),
    sender = CustomerUiModel(name = "Jane Smith"),
    operations = OperationsUiModel(
        highlight = false,
    )
)

val mockShipment2 = ShipmentUiModel(
    number = "9876543210987654321",
    shipmentType = ShipmentTypeUiModel.COURIER,
    status = ShipmentStatusUiModel.DELIVERED,
    eventLogUiModel = listOf(
        EventLogUiModel(
            name = "Shipment picked up from sender",
            date = ZonedDateTime.now().minusDays(5)
        ),
        EventLogUiModel(name = "Shipment in transit", date = ZonedDateTime.now().minusDays(3)),
        EventLogUiModel(
            name = "Shipment delivered to receiver",
            date = ZonedDateTime.now().minusDays(1)
        )
    ),
    openCode = null,
    expiryDate = null,
    storedDate = ZonedDateTime.now().minusDays(3),
    pickUpDate = ZonedDateTime.now().minusDays(1),
    receiver = CustomerUiModel(name = "Alice Johnson"),
    sender = CustomerUiModel(name = "Bob Enterprises"),
    operations = OperationsUiModel(
        highlight = false,
    )
)

val mockShipment3 = ShipmentUiModel(
    number = "112233445566778899",
    shipmentType = ShipmentTypeUiModel.PARCEL_LOCKER,
    status = ShipmentStatusUiModel.READY_TO_PICKUP,
    eventLogUiModel = listOf(
        EventLogUiModel(name = "Shipment dispatched", date = ZonedDateTime.now().minusDays(7))
    ),
    openCode = null,
    expiryDate = null,
    storedDate = null,
    pickUpDate = null,
    receiver = null,
    sender = CustomerUiModel(name = "Warehouse A"),
    operations = OperationsUiModel(
        highlight = false,
    )
)
