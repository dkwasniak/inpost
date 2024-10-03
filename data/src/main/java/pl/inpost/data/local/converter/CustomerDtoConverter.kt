package pl.inpost.data.local.converter

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import pl.inpost.data.model.CustomerDto

class CustomerDtoConverter {

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val customerDtoAdapter: JsonAdapter<CustomerDto> = moshi.adapter(CustomerDto::class.java)

    @TypeConverter
    fun fromCustomerDto(customer: CustomerDto?): String? {
        return customer?.let { customerDtoAdapter.toJson(it) }
    }

    @TypeConverter
    fun toCustomerDto(customerString: String?): CustomerDto? {
        return customerString?.let { customerDtoAdapter.fromJson(it) }
    }
}