package pl.inpost.domain.usecase

import kotlinx.coroutines.flow.Flow
import pl.inpost.domain.model.Shipment
import pl.inpost.domain.repository.ShipmentRepository
import javax.inject.Inject

class GetShipmentUseCase @Inject constructor(
    private val shipmentRepository: ShipmentRepository
){

    fun execute(): Flow<List<Shipment>> {
        return shipmentRepository.getShipment()
    }
}