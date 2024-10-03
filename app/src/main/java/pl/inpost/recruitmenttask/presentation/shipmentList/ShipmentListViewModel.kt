package pl.inpost.recruitmenttask.presentation.shipmentList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.inpost.domain.usecase.GetShipmentUseCase
import pl.inpost.domain.remotedata.RemoteData
import pl.inpost.domain.remotedata.map
import pl.inpost.domain.usecase.ArchiveShipmentUseCase
import pl.inpost.recruitmenttask.mapper.toUiModel
import pl.inpost.recruitmenttask.model.GroupedShipmentsUiModel
import pl.inpost.recruitmenttask.model.removeShipment
import javax.inject.Inject

data class ShipmentUiState(
    val shipments: RemoteData<Throwable, GroupedShipmentsUiModel> = RemoteData.NotAsked
)

@HiltViewModel
class ShipmentListViewModel @Inject constructor(
    private val getShipmentUseCase: GetShipmentUseCase,
    private val archiveShipmentUseCase: ArchiveShipmentUseCase
) : ViewModel() {

    private val stateFlow = MutableStateFlow(ShipmentUiState())

    fun stateFlow(): StateFlow<ShipmentUiState> = stateFlow.asStateFlow()

    init {
        refreshData()
    }

    fun refresh() {
        refreshData()
    }

    fun archiveShipment(shipmentNumber: String) {
        viewModelScope.launch {
            archiveShipmentUseCase.execute(shipmentNumber)

            val currentShipments = stateFlow.value.shipments
            if (currentShipments is RemoteData.Success) {
                val updatedGroupedShipments = currentShipments.data.removeShipment(shipmentNumber)
                stateFlow.value = stateFlow.value.copy(
                    shipments = RemoteData.Success(updatedGroupedShipments)
                )
            }
        }
    }
    private fun refreshData() {
        stateFlow.value = stateFlow.value.copy(shipments = RemoteData.Loading)
        viewModelScope.launch {
            getShipmentUseCase.execute().collect {
                stateFlow.value =
                    stateFlow.value.copy(shipments = it.map { shipments ->
                        shipments.toUiModel()
                    })
            }
        }
    }
}
