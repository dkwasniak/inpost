package pl.inpost.recruitmenttask.model

import org.threeten.bp.ZonedDateTime


data class ShipmentUiModel(
    val number: String,
    val shipmentType: ShipmentTypeUiModel,
    val status: ShipmentStatusUiModel,
    val eventLogUiModel: List<EventLogUiModel>,
    val openCode: String?,
    val expiryDate: ZonedDateTime?,
    val storedDate: ZonedDateTime?,
    val pickUpDate: ZonedDateTime?,
    val receiver: CustomerUiModel?,
    val sender: CustomerUiModel?,
    val operations: OperationsUiModel,
    val detailsStatus: DetailsStatusUiModel? = null
)
