package pl.inpost.data.local.converter

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import pl.inpost.data.model.OperationsDto

class OperationsDtoConverter {

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val operationDtoAdapter: JsonAdapter<OperationsDto> = moshi.adapter(OperationsDto::class.java)

    @TypeConverter
    fun fromOperationsDto(operations: OperationsDto?): String? {
        return operations?.let { operationDtoAdapter.toJson(it) }
    }

    @TypeConverter
    fun toOperationsDto(operationsString: String?): OperationsDto? {
        return operationsString?.let { operationDtoAdapter.fromJson(it) }
    }
}