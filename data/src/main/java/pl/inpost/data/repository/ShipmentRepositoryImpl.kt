package pl.inpost.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import pl.inpost.data.local.ShipmentLocalDataSource
import pl.inpost.data.mapper.toDomain
import pl.inpost.data.mapper.toDto
import pl.inpost.data.network.api.shipment.ShipmentApi
import pl.inpost.domain.model.Shipment
import pl.inpost.domain.model.ShipmentStatus
import pl.inpost.domain.repository.ShipmentRepository
import javax.inject.Inject

class ShipmentRepositoryImpl @Inject constructor(
    private val shipmentApi: ShipmentApi,
    private val localDataSource: ShipmentLocalDataSource
) : ShipmentRepository {

    override fun getShipment(): Flow<List<Shipment>> {
        val archivedShipmentsFlow = localDataSource.getArchivedShipments()

        val apiShipmentsFlow = flow {
            val apiShipments = try {
                shipmentApi.getShipments()
                    .map { it.toDomain() }
                    .filter { it.status != ShipmentStatus.NOT_READY }
            } catch (e: Exception) {
                emit(emptyList())
                return@flow
            }

            localDataSource.insertShipments(apiShipments.map { it.toDto() })
            emit(apiShipments)
        }

        return apiShipmentsFlow.combine(archivedShipmentsFlow) { apiShipments, archivedShipments ->
            val archivedNumbers = archivedShipments.map { it.number }
            apiShipments.filterNot { it.number in archivedNumbers }
        }
    }

    override suspend fun archiveShipment(number: String) {
        localDataSource.archiveShipment(number)
    }

    override suspend fun unarchiveShipment(number: String) {
        localDataSource.unarchiveShipment(number)
    }
}