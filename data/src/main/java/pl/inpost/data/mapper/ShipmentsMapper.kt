package pl.inpost.data.mapper

import pl.inpost.data.model.ShipmentsDto
import pl.inpost.domain.model.Shipments

fun ShipmentsDto.toDomain(): Shipments {
    return Shipments(
        shipments = this.shipments.map { it.toDomain() }
    )
}