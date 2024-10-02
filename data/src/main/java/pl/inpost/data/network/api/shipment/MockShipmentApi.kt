package pl.inpost.data.network.api.shipment

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import pl.inpost.data.R
import pl.inpost.data.adapter.ZonedDateTimeApiTypeAdapter
import pl.inpost.data.model.ShipmentDto
import pl.inpost.data.model.ShipmentsDto
import pl.inpost.domain.model.ShipmentStatus
import pl.inpost.domain.model.Customer
import pl.inpost.domain.model.EventLog
import pl.inpost.domain.model.Operations
import pl.inpost.domain.model.Shipment
import pl.inpost.domain.model.ShipmentType
import java.time.ZonedDateTime
import kotlin.random.Random

class MockShipmentApi(
    @ApplicationContext private val context: Context,
    zonedDateTimeApiTypeAdapter: ZonedDateTimeApiTypeAdapter
) : ShipmentApi {

    private val response by lazy {
        val json = context.resources.openRawResource(R.raw.mock_shipment_api_response)
            .bufferedReader()
            .use { it.readText() }

        val jsonAdapter = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(zonedDateTimeApiTypeAdapter)
            .build()
            .adapter(ShipmentsDto::class.java)

        jsonAdapter.fromJson(json) as ShipmentsDto
    }
    private var firstUse = true

    override suspend fun getShipments(): List<ShipmentDto> {
        delay(1000)
        return if (firstUse) {
            firstUse = false
            emptyList()
        } else {
            response.shipments
        }
    }
}

private fun mockShipmentNetwork(
    number: String = Random.nextLong(1, 9999_9999_9999_9999).toString(),
    type: ShipmentType = ShipmentType.PARCEL_LOCKER,
    status: ShipmentStatus = ShipmentStatus.DELIVERED,
    sender: Customer? = mockCustomerNetwork(),
    receiver: Customer? = mockCustomerNetwork(),
    operations: Operations = mockOperationsNetwork(),
    eventLog: List<EventLog> = emptyList(),
    openCode: String? = null,
    expireDate: ZonedDateTime? = null,
    storedDate: ZonedDateTime? = null,
    pickupDate: ZonedDateTime? = null
) = Shipment(
    number = number,
    shipmentType = type,
    status = status,
    eventLog = eventLog,
    openCode = openCode,
    expiryDate = expireDate,
    storedDate = storedDate,
    pickUpDate = pickupDate,
    receiver = receiver,
    sender = sender,
    operations = operations
)

private fun mockCustomerNetwork(
    email: String = "name@email.com",
    phoneNumber: String = "123 123 123",
    name: String = "Jan Kowalski"
) = Customer(
    email = email,
    phoneNumber = phoneNumber,
    name = name
)

private fun mockOperationsNetwork(
    manualArchive: Boolean = false,
    delete: Boolean = false,
    collect: Boolean = false,
    highlight: Boolean = false,
    expandAvizo: Boolean = false,
    endOfWeekCollection: Boolean = false
) = Operations(
    manualArchive = manualArchive,
    delete = delete,
    collect = collect,
    highlight = highlight,
    expandAvizo = expandAvizo,
    endOfWeekCollection = endOfWeekCollection
)