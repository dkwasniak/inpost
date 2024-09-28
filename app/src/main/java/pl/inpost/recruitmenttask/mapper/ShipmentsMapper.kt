package pl.inpost.recruitmenttask.mapper

import pl.inpost.domain.model.Shipments
import pl.inpost.recruitmenttask.model.ShipmentsUiModel

fun Shipments.toUiModel(): ShipmentsUiModel {
    return ShipmentsUiModel(
        shipments = this.shipments.map { it.toUiModel() }
    )
}