package pl.inpost.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.ZonedDateTime


@Entity(tableName = "shipments")
data class ShipmentDto(
    @PrimaryKey val number: String,
    val shipmentType: ShipmentTypeDto,
    val status: ShipmentStatusDto,
    val eventLog: List<EventLogDto>,
    val openCode: String?,
    val expiryDate: ZonedDateTime?,
    val storedDate: ZonedDateTime?,
    val pickUpDate: ZonedDateTime?,
    val receiver: CustomerDto?,
    val sender: CustomerDto?,
    val operations: OperationsDto,
    val isArchived: Boolean = false
)
