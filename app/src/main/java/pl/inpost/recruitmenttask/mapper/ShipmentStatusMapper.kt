package pl.inpost.recruitmenttask.mapper

import pl.inpost.domain.model.ShipmentStatus
import pl.inpost.recruitmenttask.model.ShipmentStatusUiModel

fun ShipmentStatus.toUiModel(): ShipmentStatusUiModel {
    return when (this) {
        ShipmentStatus.ADOPTED_AT_SORTING_CENTER -> ShipmentStatusUiModel.ADOPTED_AT_SORTING_CENTER
        ShipmentStatus.SENT_FROM_SORTING_CENTER -> ShipmentStatusUiModel.SENT_FROM_SORTING_CENTER
        ShipmentStatus.ADOPTED_AT_SOURCE_BRANCH -> ShipmentStatusUiModel.ADOPTED_AT_SOURCE_BRANCH
        ShipmentStatus.SENT_FROM_SOURCE_BRANCH -> ShipmentStatusUiModel.SENT_FROM_SOURCE_BRANCH
        ShipmentStatus.AVIZO -> ShipmentStatusUiModel.AVIZO
        ShipmentStatus.CONFIRMED -> ShipmentStatusUiModel.CONFIRMED
        ShipmentStatus.CREATED -> ShipmentStatusUiModel.CREATED
        ShipmentStatus.DELIVERED -> ShipmentStatusUiModel.DELIVERED
        ShipmentStatus.OTHER -> ShipmentStatusUiModel.OTHER
        ShipmentStatus.OUT_FOR_DELIVERY -> ShipmentStatusUiModel.OUT_FOR_DELIVERY
        ShipmentStatus.PICKUP_TIME_EXPIRED -> ShipmentStatusUiModel.PICKUP_TIME_EXPIRED
        ShipmentStatus.READY_TO_PICKUP -> ShipmentStatusUiModel.READY_TO_PICKUP
        ShipmentStatus.RETURNED_TO_SENDER -> ShipmentStatusUiModel.RETURNED_TO_SENDER
    }
}