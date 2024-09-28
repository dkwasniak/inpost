package pl.inpost.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.inpost.data.repository.ShipmentRepositoryImpl
import pl.inpost.domain.repository.ShipmentRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindShipmentRepository(
        shipmentRepository: ShipmentRepositoryImpl
    ): ShipmentRepository
}