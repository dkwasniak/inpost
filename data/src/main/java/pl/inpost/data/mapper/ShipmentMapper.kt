package pl.inpost.data.mapper

import pl.inpost.data.model.ShipmentDto
import pl.inpost.domain.model.Shipment

fun ShipmentDto.toDomain(): Shipment {
    return Shipment(
        number = this.number,
        shipmentType = this.shipmentType.toDomain(),
        status = this.status.toDomain(),
        eventLog = this.eventLog.map { it.toDomain() },
        openCode = this.openCode,
        expiryDate = this.expiryDate,
        storedDate = this.storedDate,
        pickUpDate = this.pickUpDate,
        receiver = this.receiver?.toDomain(),
        sender = this.sender?.toDomain(),
        operations = this.operations.toDomain()
    )
}