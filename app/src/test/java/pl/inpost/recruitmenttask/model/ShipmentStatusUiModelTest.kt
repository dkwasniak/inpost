package pl.inpost.recruitmenttask.model

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.inpost.recruitmenttask.R

class ShipmentStatusUiModelTest {

    @Test
    fun `should maintain the correct order of statuses`() {
        // Given: Expected order of statuses based on the comment in the code
        val expectedOrder = listOf(
            ShipmentStatusUiModel.CREATED,                // Priority 1
            ShipmentStatusUiModel.CONFIRMED,              // Priority 2
            ShipmentStatusUiModel.ADOPTED_AT_SOURCE_BRANCH, // Priority 3
            ShipmentStatusUiModel.SENT_FROM_SOURCE_BRANCH,  // Priority 4
            ShipmentStatusUiModel.ADOPTED_AT_SORTING_CENTER, // Priority 5
            ShipmentStatusUiModel.SENT_FROM_SORTING_CENTER,  // Priority 6
            ShipmentStatusUiModel.OTHER,                  // Priority 7
            ShipmentStatusUiModel.DELIVERED,              // Priority 8
            ShipmentStatusUiModel.RETURNED_TO_SENDER,     // Priority 9
            ShipmentStatusUiModel.AVIZO,                  // Priority 10
            ShipmentStatusUiModel.OUT_FOR_DELIVERY,       // Priority 11
            ShipmentStatusUiModel.READY_TO_PICKUP,        // Priority 12
            ShipmentStatusUiModel.PICKUP_TIME_EXPIRED     // Priority 13
        )

        // When: The actual order of statuses in the enum
        val actualOrder = ShipmentStatusUiModel.values().toList()

        // Then: Verify that the order matches the expected order
        assertEquals("The order of ShipmentStatusUiModel enum values is incorrect.", expectedOrder, actualOrder)
    }

    @Test
    fun `should use correct string resources for each status`() {
        // Given: Expected mapping of statuses to string resources
        val expectedMappings = mapOf(
            ShipmentStatusUiModel.ADOPTED_AT_SORTING_CENTER to R.string.status_adopted_at_sorting_center,
            ShipmentStatusUiModel.SENT_FROM_SORTING_CENTER to R.string.status_sent_from_sorting_center,
            ShipmentStatusUiModel.ADOPTED_AT_SOURCE_BRANCH to R.string.status_adopted_at_source_branch,
            ShipmentStatusUiModel.SENT_FROM_SOURCE_BRANCH to R.string.status_sent_from_source_branch,
            ShipmentStatusUiModel.AVIZO to R.string.status_avizo,
            ShipmentStatusUiModel.CONFIRMED to R.string.status_confirmed,
            ShipmentStatusUiModel.CREATED to R.string.status_created,
            ShipmentStatusUiModel.DELIVERED to R.string.status_delivered,
            ShipmentStatusUiModel.OTHER to R.string.status_other,
            ShipmentStatusUiModel.OUT_FOR_DELIVERY to R.string.status_out_for_delivery,
            ShipmentStatusUiModel.PICKUP_TIME_EXPIRED to R.string.status_pickup_time_expired,
            ShipmentStatusUiModel.READY_TO_PICKUP to R.string.status_ready_to_pickup,
            ShipmentStatusUiModel.RETURNED_TO_SENDER to R.string.status_returned_to_sender
        )

        // When & Then: Verify that each status has the correct string resource
        ShipmentStatusUiModel.values().forEach { status ->
            val expectedRes = expectedMappings[status]
            assertEquals("Incorrect string resource for status: ${status.name}", expectedRes, status.nameRes)
        }
    }

    @Test
    fun `should have all statuses defined in correct order`() {
        // Given: Expected number of statuses
        val expectedStatusCount = 13

        // When: The actual number of statuses in the enum
        val actualStatusCount = ShipmentStatusUiModel.values().size

        // Then: Verify that the number of statuses matches the expected count
        assertEquals("The number of ShipmentStatusUiModel values is incorrect.", expectedStatusCount, actualStatusCount)
    }
}
