package pl.inpost.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pl.inpost.data.model.ShipmentDto

@Dao
interface ShipmentDao {

    @Query("SELECT * FROM shipments WHERE isArchived = 0")
    fun getAllNonArchivedShipments(): Flow<List<ShipmentDto>>

    @Query("SELECT * FROM shipments WHERE isArchived = 1")
    fun getArchivedShipments(): Flow<List<ShipmentDto>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertShipments(shipments: List<ShipmentDto>)

    @Query("UPDATE shipments SET isArchived = 1 WHERE number = :shipmentNumber")
    suspend fun archiveShipment(shipmentNumber: String)

    @Query("UPDATE shipments SET isArchived = 0 WHERE number = :shipmentNumber")
    suspend fun unarchiveShipment(shipmentNumber: String)
}