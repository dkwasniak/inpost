package pl.inpost.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

class ShipmentStatusTest {

    @Test
    fun `should maintain the correct order of statuses`() {
        // Given: Expected order of statuses based on the comment in the code
        val expectedOrder = listOf(
            ShipmentStatus.CREATED,
            ShipmentStatus.CONFIRMED,
            ShipmentStatus.ADOPTED_AT_SOURCE_BRANCH,
            ShipmentStatus.SENT_FROM_SOURCE_BRANCH,
            ShipmentStatus.ADOPTED_AT_SORTING_CENTER,
            ShipmentStatus.SENT_FROM_SORTING_CENTER,
            ShipmentStatus.OTHER,
            ShipmentStatus.DELIVERED,
            ShipmentStatus.RETURNED_TO_SENDER,
            ShipmentStatus.AVIZO,
            ShipmentStatus.OUT_FOR_DELIVERY,
            ShipmentStatus.READY_TO_PICKUP,
            ShipmentStatus.PICKUP_TIME_EXPIRED
        )

        // When: The actual order of statuses in the enum
        val actualOrder = ShipmentStatus.values().toList()

        // Then: Verify that the order matches the expected order
        assertEquals("The order of ShipmentStatus enum values is incorrect.", expectedOrder, actualOrder)
    }

    @Test
    fun `should have all statuses defined in correct order`() {
        // Given: Expected number of statuses
        val expectedStatusCount = 13

        // When: The actual number of statuses in the enum
        val actualStatusCount = ShipmentStatus.values().size

        // Then: Verify that the number of statuses matches the expected count
        assertEquals("The number of ShipmentStatus values is incorrect.", expectedStatusCount, actualStatusCount)
    }
}
