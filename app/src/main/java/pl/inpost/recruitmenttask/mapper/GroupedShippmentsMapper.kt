package pl.inpost.recruitmenttask.mapper

import pl.inpost.domain.model.GroupedShipments
import pl.inpost.recruitmenttask.model.GroupedShipmentsUiModel

fun GroupedShipments.toUiModel(): GroupedShipmentsUiModel {
    return GroupedShipmentsUiModel(
        readyToPickup = this.readyToPickup.map { it.toUiModel() },
        other = this.other.map { it.toUiModel() }
    )
}