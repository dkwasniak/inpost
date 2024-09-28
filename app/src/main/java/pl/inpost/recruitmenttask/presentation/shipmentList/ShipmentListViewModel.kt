package pl.inpost.recruitmenttask.presentation.shipmentList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.inpost.domain.model.Shipment
import pl.inpost.domain.usecase.GetShipmentUseCase
import pl.inpost.recruitmenttask.extension.remotelify
import pl.inpost.recruitmenttask.mapper.toUiModel
import pl.inpost.recruitmenttask.model.ShipmentUiModel
import pl.inpost.recruitmenttask.remotedata.bind
import pl.inpost.recruitmenttask.util.setState
import javax.inject.Inject

@HiltViewModel
class ShipmentListViewModel @Inject constructor(
    private val getShipmentUseCase: GetShipmentUseCase
) : ViewModel() {

    private val mutableViewState = MutableLiveData<List<ShipmentUiModel>>(emptyList())
    val viewState: LiveData<List<ShipmentUiModel>> = mutableViewState

    init {
        refreshData()
    }

    private fun refreshData() {
        viewModelScope.launch {
            getShipmentUseCase.execute().remotelify().collect {
                it.bind(
                    dataBinder = { data ->
                        data?.let {
                            mutableViewState.setState { data.map { shipment -> shipment.toUiModel() } }
                        }
                    },
                    loadingBinder = {

                    }
                )
            }

        }
    }
}
