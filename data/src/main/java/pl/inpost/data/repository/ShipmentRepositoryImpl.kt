package pl.inpost.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.inpost.data.mapper.toDomain
import pl.inpost.data.network.api.shipment.ShipmentApi
import pl.inpost.domain.model.Shipment
import pl.inpost.domain.repository.ShipmentRepository
import javax.inject.Inject

class ShipmentRepositoryImpl @Inject constructor(
    private val shipmentApi: ShipmentApi
) : ShipmentRepository {

    override fun getShipment(): Flow<List<Shipment>> {
        return flow {
            emit(shipmentApi.getShipments().map { it.toDomain() })
        }
    }
}