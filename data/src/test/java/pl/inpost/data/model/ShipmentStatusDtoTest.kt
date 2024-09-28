package pl.inpost.data.model

import org.junit.Assert.assertEquals
import org.junit.Test

class ShipmentStatusDtoTest {

    @Test
    fun `should maintain the correct order of statuses`() {
        // Given: Expected order of statuses based on the comment in the code
        val expectedOrder = listOf(
            ShipmentStatusDto.CREATED,                 // Priority 1
            ShipmentStatusDto.CONFIRMED,               // Priority 2
            ShipmentStatusDto.ADOPTED_AT_SOURCE_BRANCH, // Priority 3
            ShipmentStatusDto.SENT_FROM_SOURCE_BRANCH,  // Priority 4
            ShipmentStatusDto.ADOPTED_AT_SORTING_CENTER, // Priority 5
            ShipmentStatusDto.SENT_FROM_SORTING_CENTER,  // Priority 6
            ShipmentStatusDto.OTHER,                   // Priority 7
            ShipmentStatusDto.DELIVERED,               // Priority 8
            ShipmentStatusDto.RETURNED_TO_SENDER,      // Priority 9
            ShipmentStatusDto.AVIZO,                   // Priority 10
            ShipmentStatusDto.OUT_FOR_DELIVERY,        // Priority 11
            ShipmentStatusDto.READY_TO_PICKUP,         // Priority 12
            ShipmentStatusDto.PICKUP_TIME_EXPIRED      // Priority 13
        )

        // When: The actual order of statuses in the enum
        val actualOrder = ShipmentStatusDto.values().toList()

        // Then: Verify that the order matches the expected order
        assertEquals("The order of ShipmentStatusDto enum values is incorrect.", expectedOrder, actualOrder)
    }

    @Test
    fun `should have all statuses defined in correct order`() {
        // Given: Expected number of statuses
        val expectedStatusCount = 13

        // When: The actual number of statuses in the enum
        val actualStatusCount = ShipmentStatusDto.values().size

        // Then: Verify that the number of statuses matches the expected count
        assertEquals("The number of ShipmentStatusDto values is incorrect.", expectedStatusCount, actualStatusCount)
    }
}
