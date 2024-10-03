package pl.inpost.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.inpost.data.local.ShipmentDao
import pl.inpost.data.local.ShipmentDatabase
import pl.inpost.data.local.ShipmentLocalDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ShipmentDatabase {
        return Room.databaseBuilder(context, ShipmentDatabase::class.java, "shipment_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideShipmentDao(database: ShipmentDatabase): ShipmentDao {
        return database.shipmentDao()
    }

    @Provides
    @Singleton
    fun provideShipmentLocalDataSource(dao: ShipmentDao): ShipmentLocalDataSource {
        return ShipmentLocalDataSource(dao)
    }
}