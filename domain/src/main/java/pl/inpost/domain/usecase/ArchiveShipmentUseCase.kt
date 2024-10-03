package pl.inpost.domain.usecase

import pl.inpost.domain.repository.ShipmentRepository
import javax.inject.Inject

class ArchiveShipmentUseCase @Inject constructor(
    private val shipmentRepository: ShipmentRepository
) {

    suspend fun execute(shipmentNumber: String) {
        shipmentRepository.archiveShipment(shipmentNumber)
    }
}