package pl.inpost.data.mapper

import pl.inpost.domain.model.ShipmentStatus
import pl.inpost.data.model.ShipmentStatusDto

fun ShipmentStatusDto.toDomain(): ShipmentStatus {
    return when (this) {
        ShipmentStatusDto.ADOPTED_AT_SORTING_CENTER -> ShipmentStatus.ADOPTED_AT_SORTING_CENTER
        ShipmentStatusDto.SENT_FROM_SORTING_CENTER -> ShipmentStatus.SENT_FROM_SORTING_CENTER
        ShipmentStatusDto.ADOPTED_AT_SOURCE_BRANCH -> ShipmentStatus.ADOPTED_AT_SOURCE_BRANCH
        ShipmentStatusDto.SENT_FROM_SOURCE_BRANCH -> ShipmentStatus.SENT_FROM_SOURCE_BRANCH
        ShipmentStatusDto.AVIZO -> ShipmentStatus.AVIZO
        ShipmentStatusDto.CONFIRMED -> ShipmentStatus.CONFIRMED
        ShipmentStatusDto.CREATED -> ShipmentStatus.CREATED
        ShipmentStatusDto.DELIVERED -> ShipmentStatus.DELIVERED
        ShipmentStatusDto.OTHER -> ShipmentStatus.OTHER
        ShipmentStatusDto.OUT_FOR_DELIVERY -> ShipmentStatus.OUT_FOR_DELIVERY
        ShipmentStatusDto.PICKUP_TIME_EXPIRED -> ShipmentStatus.PICKUP_TIME_EXPIRED
        ShipmentStatusDto.READY_TO_PICKUP -> ShipmentStatus.READY_TO_PICKUP
        ShipmentStatusDto.RETURNED_TO_SENDER -> ShipmentStatus.RETURNED_TO_SENDER
        ShipmentStatusDto.NOT_READY -> ShipmentStatus.NOT_READY

    }
}