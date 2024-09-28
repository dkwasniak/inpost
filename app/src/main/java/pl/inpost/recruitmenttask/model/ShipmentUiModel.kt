package pl.inpost.recruitmenttask.model

import java.time.ZonedDateTime

data class ShipmentUiModel(
    val number: String,
    val shipmentType: String,
    val status: String,
    val eventLogUiModel: List<EventLogUiModel>,
    val openCode: String?,
    val expiryDate: ZonedDateTime?,
    val storedDate: ZonedDateTime?,
    val pickUpDate: ZonedDateTime?,
    val receiver: CustomerUiModel?,
    val sender: CustomerUiModel?,
    val operationsUiModel: OperationsUiModel
)
