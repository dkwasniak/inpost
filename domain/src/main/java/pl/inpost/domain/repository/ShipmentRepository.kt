package pl.inpost.domain.repository

import kotlinx.coroutines.flow.Flow
import pl.inpost.domain.model.Shipment

interface ShipmentRepository {
    fun getShipment(): Flow<List<Shipment>>

    suspend fun archiveShipment(number: String)

    suspend fun unarchiveShipment(number: String)
}