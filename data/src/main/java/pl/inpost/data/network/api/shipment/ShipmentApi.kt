package pl.inpost.data.network.api.shipment

import pl.inpost.data.model.ShipmentDto

interface ShipmentApi {
    suspend fun getShipments(): List<ShipmentDto>
}
