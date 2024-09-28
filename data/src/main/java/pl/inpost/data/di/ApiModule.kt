package pl.inpost.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.inpost.data.adapter.ZonedDateTimeApiTypeAdapter
import pl.inpost.data.network.api.shipment.MockShipmentApi
import pl.inpost.data.network.api.shipment.ShipmentApi
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Provides
    @Singleton
    fun shipmentApi(
        @ApplicationContext context: Context,
        zonedDateTimeApiTypeAdapter: ZonedDateTimeApiTypeAdapter
    ): ShipmentApi = MockShipmentApi(context, zonedDateTimeApiTypeAdapter)
}