package pl.inpost.recruitmenttask.presentation.shipmentList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.model.ShipmentUiModel
import pl.inpost.recruitmenttask.presentation.shipmentList.preview.mockShipment1
import pl.inpost.recruitmenttask.presentation.shipmentList.preview.mockShipment2
import pl.inpost.recruitmenttask.presentation.shipmentList.preview.mockShipment3
import pl.inpost.recruitmenttask.theme.InpostColors
import pl.inpost.recruitmenttask.theme.h6
import pl.inpost.recruitmenttask.theme.h9
import pl.inpost.recruitmenttask.theme.status
import pl.inpost.recruitmenttask.theme.subtitle


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableShipmentCard(
    shipment: ShipmentUiModel,
    onArchive: () -> Unit
) {
    // DismissState with a conditional check for manualArchive
    val dismissState = rememberDismissState(
        confirmStateChange = { dismissValue ->
            // Allow dismiss only if the item supports manual archive
            if (dismissValue == DismissValue.DismissedToStart && shipment.operations.manualArchive) {
                onArchive()
                true
            } else {
                false
            }
        }
    )

    // Use directions based on `manualArchive` property
    val directions = if (shipment.operations.manualArchive) {
        setOf(DismissDirection.EndToStart)
    } else {
        emptySet() // Disable swipe if `manualArchive` is false
    }

    SwipeToDismiss(
        state = dismissState,
        directions = directions,
        background = { ArchiveBackground() },
        dismissContent = {
            ShipmentCard(shipment)
        }
    )
}


@Composable
private fun ArchiveBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
            .padding(16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(
            text = stringResource(R.string.shipment_archive),
            style = MaterialTheme.typography.h6,
            color = Color.White
        )
    }
}

@Composable
fun ShipmentCard(
    shipment: ShipmentUiModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RectangleShape,
        colors = CardColors(
            contentColor = MaterialTheme.colorScheme.onSurface,
            containerColor = MaterialTheme.colorScheme.surface,
            disabledContentColor = CardDefaults.cardColors().disabledContentColor,
            disabledContainerColor = CardDefaults.cardColors().disabledContainerColor
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.shipment_card_parcel_number_label).uppercase(),
                        style = MaterialTheme.typography.subtitle,
                        color = InpostColors.Grey
                    )
                    Text(
                        text = shipment.number,
                        style = MaterialTheme.typography.h6,
                        color = InpostColors.DarkerGrey
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.paczkomat),
                    contentDescription = stringResource(R.string.accessibility_paczkomat_image)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            val fraction = if (shipment.detailsStatus != null) 1f / 3f else 1f
            Row {
                Column(
                    modifier = Modifier.fillMaxWidth(fraction)
                ) {
                    Text(
                        text = stringResource(R.string.shipment_card_status_label).uppercase(),
                        style = MaterialTheme.typography.subtitle,
                        color = InpostColors.Grey
                    )
                    Text(
                        text = stringResource(shipment.status.nameRes),
                        style = MaterialTheme.typography.status,
                        color = InpostColors.DarkerGrey,
                    )
                }
                if (shipment.detailsStatus != null) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(shipment.detailsStatus.resId).uppercase(),
                            style = MaterialTheme.typography.subtitle,
                            textAlign = TextAlign.End,
                            color = InpostColors.Grey
                        )
                        Text(
                            text = shipment.detailsStatus.formattedDate,
                            style = MaterialTheme.typography.h6,
                            color = InpostColors.DarkerGrey
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.shipment_card_sender_label).uppercase(),
                        style = MaterialTheme.typography.subtitle,
                        color = InpostColors.Grey
                    )
                    Text(
                        text = shipment.sender?.name ?: "",
                        style = MaterialTheme.typography.status,
                        color = InpostColors.DarkerGrey,
                    )
                }

                // More arrow link
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(24.dp)
                        .clickable {

                        }
                ) {
                    Text(
                        text = stringResource(R.string.shipment_card_more_label),
                        style = MaterialTheme.typography.h9,
                        color = InpostColors.DarkerGrey,
                    )
                    Spacer(Modifier.width(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.arrow_right),
                        contentDescription = stringResource(R.string.accessibility_right_pointing_arrow)
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewShipmentCard(
    @PreviewParameter(ShipmentCardPreviewParameterProvider::class) data: ShipmentUiModel
) {
    MaterialTheme {
        ShipmentCard(data)
    }
}

class ShipmentCardPreviewParameterProvider : PreviewParameterProvider<ShipmentUiModel> {
    override val values = sequenceOf(
       mockShipment1, mockShipment2, mockShipment3
    )
}