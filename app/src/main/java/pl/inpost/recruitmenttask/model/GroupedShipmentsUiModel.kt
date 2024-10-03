package pl.inpost.recruitmenttask.model


data class GroupedShipmentsUiModel(
    val readyToPickup: List<ShipmentUiModel>,
    val other: List<ShipmentUiModel>
)

fun GroupedShipmentsUiModel.removeShipment(shipmentNumber: String): GroupedShipmentsUiModel {
    return GroupedShipmentsUiModel(
        readyToPickup = this.readyToPickup.filterNot { it.number == shipmentNumber },
        other = this.other.filterNot { it.number == shipmentNumber }
    )
}