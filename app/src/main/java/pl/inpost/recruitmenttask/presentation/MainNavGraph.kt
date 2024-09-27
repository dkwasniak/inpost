package pl.inpost.recruitmenttask.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pl.inpost.recruitmenttask.presentation.shipmentList.ShipmentScreen

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.SHIPMENT_ROUTE,
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            Destinations.SHIPMENT_ROUTE
        ) {
            ShipmentScreen()
        }
    }
}