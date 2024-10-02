package pl.inpost.recruitmenttask.mapper

import pl.inpost.domain.model.ShipmentType
import pl.inpost.recruitmenttask.model.ShipmentTypeUiModel


fun ShipmentType.toUiModel(): ShipmentTypeUiModel {
    return when (this) {
        ShipmentType.PARCEL_LOCKER -> ShipmentTypeUiModel.PARCEL_LOCKER
        ShipmentType.COURIER -> ShipmentTypeUiModel.COURIER
    }
}