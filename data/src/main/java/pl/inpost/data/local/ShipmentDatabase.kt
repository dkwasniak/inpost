package pl.inpost.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.inpost.data.local.converter.CustomerDtoConverter
import pl.inpost.data.local.converter.EventLogListConverter
import pl.inpost.data.local.converter.OperationsDtoConverter
import pl.inpost.data.local.converter.ZoneIdConverter
import pl.inpost.data.local.converter.ZonedDateTimeConverter
import pl.inpost.data.model.ShipmentDto

@Database(entities = [ShipmentDto::class], version = 1, exportSchema = false)
@TypeConverters(
    CustomerDtoConverter::class,
    OperationsDtoConverter::class,
    ZonedDateTimeConverter::class,
    EventLogListConverter::class,
    ZoneIdConverter::class,
)
abstract class ShipmentDatabase : RoomDatabase() {
    abstract fun shipmentDao(): ShipmentDao
}