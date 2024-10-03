package pl.inpost.data.mapper

import pl.inpost.data.model.ShipmentTypeDto
import pl.inpost.domain.model.ShipmentType

fun ShipmentTypeDto.toDomain(): ShipmentType {
    return when (this) {
        ShipmentTypeDto.PARCEL_LOCKER -> ShipmentType.PARCEL_LOCKER
        ShipmentTypeDto.COURIER -> ShipmentType.COURIER
    }
}

fun ShipmentType.toDto(): ShipmentTypeDto {
    return when (this) {
        ShipmentType.PARCEL_LOCKER -> ShipmentTypeDto.PARCEL_LOCKER
        ShipmentType.COURIER -> ShipmentTypeDto.COURIER
    }
}