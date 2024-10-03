package pl.inpost.recruitmenttask.model


data class GroupedShipmentsUiModel(
    val readyToPickup: List<ShipmentUiModel>,
    val other: List<ShipmentUiModel>
)