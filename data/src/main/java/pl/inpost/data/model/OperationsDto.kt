package pl.inpost.data.model

/**
 * @param manualArchive - shipment can be manually (gesture) archived
 * @param delete - shipment can be manually deleted
 * @param collect - shipment can be remotely collected
 * @param highlight - shipment is ready to pick up - grouping
 * @param expandAvizo - shipment time to pick up can be expanded - show button
 * @param endOfWeekCollection - shipment will be available to pick up over the weekend - change colors
 */
data class OperationsDto(
    val manualArchive: Boolean = false,
    val delete: Boolean = false,
    val collect: Boolean = false,
    val highlight: Boolean = false,
    val expandAvizo: Boolean = false,
    val endOfWeekCollection: Boolean = false
)
