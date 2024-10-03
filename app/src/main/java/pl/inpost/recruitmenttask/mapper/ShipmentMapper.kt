package pl.inpost.recruitmenttask.mapper

import pl.inpost.domain.model.Shipment
import pl.inpost.domain.model.ShipmentStatus
import pl.inpost.recruitmenttask.extension.toDisplayString
import pl.inpost.recruitmenttask.model.DetailsStatusUiModel
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
        operationsUiModel = this.operations.toUiModel(),
        detailsStatus = getDetailedStatus(this.expiryDate.toDisplayString(), this.status)
    )
}

private fun getDetailedStatus(
    expiryDate: String?,
    status: ShipmentStatus
): DetailsStatusUiModel? {
    if (expiryDate != null) {
        return when (status) {
            ShipmentStatus.READY_TO_PICKUP -> {
                DetailsStatusUiModel.AwaitingCollection(expiryDate)
            }
            ShipmentStatus.DELIVERED -> {
                DetailsStatusUiModel.Delivered(expiryDate)
            }
            else -> {
                null
            }
        }
    }
    return null
}
