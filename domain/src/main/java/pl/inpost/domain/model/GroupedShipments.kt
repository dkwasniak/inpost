package pl.inpost.domain.model

data class GroupedShipments(
    val readyToPickup: List<Shipment>,
    val other: List<Shipment>
)
