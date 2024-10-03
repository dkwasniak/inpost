package pl.inpost.data.local

import kotlinx.coroutines.flow.Flow
import pl.inpost.data.model.ShipmentDto
import javax.inject.Inject

class ShipmentLocalDataSource @Inject constructor(
    private val shipmentDao: ShipmentDao
) {

    fun getAllNonArchivedShipments(): Flow<List<ShipmentDto>> {
        return shipmentDao.getAllNonArchivedShipments()
    }

    fun getArchivedShipments(): Flow<List<ShipmentDto>> {
        return shipmentDao.getArchivedShipments()
    }

    suspend fun insertShipments(shipments: List<ShipmentDto>) {
        shipmentDao.insertShipments(shipments)
    }

    suspend fun archiveShipment(shipmentNumber: String) {
        shipmentDao.archiveShipment(shipmentNumber)
    }

    suspend fun unarchiveShipment(shipmentNumber: String) {
        shipmentDao.unarchiveShipment(shipmentNumber)
    }
}