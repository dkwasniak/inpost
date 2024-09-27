package pl.inpost.recruitmenttask.presentation

import androidx.navigation.NavHostController
import pl.inpost.recruitmenttask.presentation.Screens.SHIPMENTS_SCREEN

object Screens {
    const val SHIPMENTS_SCREEN = "shipment"
}

object Destinations {
    const val SHIPMENT_ROUTE = SHIPMENTS_SCREEN
}

class MainNavigationActions(private val navController: NavHostController) {


}