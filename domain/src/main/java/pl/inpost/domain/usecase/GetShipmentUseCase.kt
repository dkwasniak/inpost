package pl.inpost.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.inpost.domain.comparator.ShipmentComparator
import pl.inpost.domain.extension.remotelify
import pl.inpost.domain.model.GroupedShipments
import pl.inpost.domain.remotedata.RemoteData
import pl.inpost.domain.repository.ShipmentRepository
import javax.inject.Inject

class GetShipmentUseCase @Inject constructor(
    private val shipmentRepository: ShipmentRepository
) {

    private val shipmentComparator = ShipmentComparator()

    fun execute(): Flow<RemoteData<Throwable, GroupedShipments>> {
        return shipmentRepository.getShipment().map {
            val (ready, other) = it.partition { it.operations.highlight }
            GroupedShipments(ready.sortedWith(shipmentComparator), other.sortedWith(shipmentComparator))
        }.remotelify()
    }
}