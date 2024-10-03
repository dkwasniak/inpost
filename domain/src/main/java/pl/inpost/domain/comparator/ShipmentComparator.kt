package pl.inpost.domain.comparator

import org.threeten.bp.ZonedDateTime
import pl.inpost.domain.model.Shipment

class ShipmentComparator : Comparator<Shipment> {

    override fun compare(shipment1: Shipment, shipment2: Shipment): Int {

        // 1. Compare status (in descending order - higher `ordinal` has higher priority)
        val statusComparison = -shipment1.status.ordinal.compareTo(shipment2.status.ordinal)
        if (statusComparison != 0) return statusComparison

        // 2. Compare `pickUpDate` (null → "9999-12-31T23:59:59Z")
        val pickUpDate1 = shipment1.pickUpDate ?: ZonedDateTime.parse("9999-12-31T23:59:59Z")
        val pickUpDate2 = shipment2.pickUpDate ?: ZonedDateTime.parse("9999-12-31T23:59:59Z")
        val pickUpDateComparison = pickUpDate1.compareTo(pickUpDate2)
        if (pickUpDateComparison != 0) return pickUpDateComparison

        // 3. Compare `expiryDate` (null → "9999-12-31T23:59:59Z")
        val expiryDate1 = shipment1.expiryDate ?: ZonedDateTime.parse("9999-12-31T23:59:59Z")
        val expiryDate2 = shipment2.expiryDate ?: ZonedDateTime.parse("9999-12-31T23:59:59Z")
        val expiryDateComparison = expiryDate1.compareTo(expiryDate2)
        if (expiryDateComparison != 0) return expiryDateComparison

        // 4. Compare `storedDate` (null → "9999-12-31T23:59:59Z")
        val storedDate1 = shipment1.storedDate ?: ZonedDateTime.parse("9999-12-31T23:59:59Z")
        val storedDate2 = shipment2.storedDate ?: ZonedDateTime.parse("9999-12-31T23:59:59Z")
        val storedDateComparison = storedDate1.compareTo(storedDate2)
        if (storedDateComparison != 0) return storedDateComparison

        // 5. Compare shipment number (`number`) lexicographically
        return shipment1.number.compareTo(shipment2.number)
    }
}