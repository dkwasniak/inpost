package pl.inpost.recruitmenttask.presentation.shipmentList

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jakewharton.threetenabp.AndroidThreeTen
import pl.inpost.domain.remotedata.RemoteData
import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.model.GroupedShipmentsUiModel
import pl.inpost.recruitmenttask.model.ShipmentUiModel
import pl.inpost.recruitmenttask.presentation.shipmentList.preview.mockShipment1
import pl.inpost.recruitmenttask.presentation.shipmentList.preview.mockShipment2
import pl.inpost.recruitmenttask.presentation.shipmentList.preview.mockShipment3
import pl.inpost.recruitmenttask.remotedata.bindComposable
import pl.inpost.recruitmenttask.theme.InpostColors
import pl.inpost.recruitmenttask.theme.h7
import pl.inpost.recruitmenttask.theme.title

@Composable
fun ShipmentScreen(
    viewModel: ShipmentListViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            AppBar()
        }
    ) { padding ->

        val viewState = viewModel.stateFlow().collectAsState().value

        Content(padding, viewState, viewModel::refresh, viewModel::archiveShipment)
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AppBar() {
    TopAppBar(title = {
        Text(
            text = stringResource(R.string.shipment_screen_title),
            style = MaterialTheme.typography.title,
            color = InpostColors.DarkerGrey,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(16.dp)
        )
    })
}

@Composable
private fun Content(
    padding: PaddingValues,
    viewState: ShipmentUiState,
    onRefresh: () -> Unit,
    archiveShipment: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(MaterialTheme.colorScheme.background)
    ) {

        viewState.shipments.bindComposable(
            loadingBinder = { loading ->
                if (loading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary,
                            strokeWidth = 4.dp
                        )
                    }
                }
            },
            dataBinder = { data ->
                ShipmentsList(data, onRefresh, archiveShipment)
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ShipmentsList(
    shipmentsList: GroupedShipmentsUiModel?,
    onRefresh: () -> Unit,
    archiveShipment: (String) -> Unit
) {

    var isRefreshing by remember { mutableStateOf(false) }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            onRefresh()
        }
    )


    Box(Modifier.pullRefresh(pullRefreshState)) {
        LazyColumn(
            modifier = Modifier.padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (shipmentsList == null || (shipmentsList.readyToPickup.isEmpty() && shipmentsList.other.isEmpty())) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                    ) {
                        Text(
                            text = "Nic tu nie ma, ale powinien być ładny empty state ;)",
                            textAlign = TextAlign.Center
                        )
                    }
                }
            } else {
                readyToCollectItemsSection(shipmentsList.readyToPickup, archiveShipment)
                otherItemsSection(shipmentsList.other, archiveShipment)
            }
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.TopCenter)
        )
    }
}

private fun LazyListScope.otherItemsSection(
    shipmentsList: List<ShipmentUiModel>,
    archiveShipment: (String) -> Unit
) {
    if(shipmentsList.isNotEmpty()) {
        item {
            SectionHeader(title = stringResource(R.string.shipment_list_other_header))
        }
        items(shipmentsList) { shipment ->
            SwipeableShipmentCard(shipment) {
                archiveShipment(shipment.number)
            }
        }
    }
}

private fun LazyListScope.readyToCollectItemsSection(
    shipmentsList: List<ShipmentUiModel>,
    archiveShipment: (String) -> Unit
) {
    if(shipmentsList.isNotEmpty()) {
        item {
            SectionHeader(title = stringResource(R.string.shipment_list_highlighted_header))
        }
        items(shipmentsList) { shipment ->
            SwipeableShipmentCard(shipment) {
                archiveShipment(shipment.number)
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.h7,
        textAlign = TextAlign.Center,
        color = InpostColors.LightGrey,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewShipmentScreenScreen() {
    AndroidThreeTen.init(LocalContext.current)
    Scaffold(
        topBar = {
            AppBar()
        }
    ) { padding ->
        Content(
            padding, ShipmentUiState(
                shipments = RemoteData.Success(
                    GroupedShipmentsUiModel(
                        listOf(mockShipment1, mockShipment2, mockShipment3),
                        listOf(mockShipment1, mockShipment2, mockShipment3)
                    )
                )
            ), {}, {}
        )
    }
}