package pl.inpost.recruitmenttask.mapper

import pl.inpost.domain.model.Shipment
import pl.inpost.recruitmenttask.model.ShipmentUiModel

fun Shipment.toUiModel(): ShipmentUiModel {
    return ShipmentUiModel(
        number = this.number,
        shipmentType = this.shipmentType.toUiModel(),
        status = this.status.toUiModel(),
        eventLogUiModel = this.eventLog.map { it.toUiModel() },
        openCode = this.openCode,
        expiryDate = this.expiryDate,
        storedDate = this.storedDate,
        pickUpDate = this.pickUpDate,
        receiver = this.receiver?.toUiModel(),
        sender = this.sender?.toUiModel(),
        operationsUiModel = this.operations.toUiModel()
    )
}