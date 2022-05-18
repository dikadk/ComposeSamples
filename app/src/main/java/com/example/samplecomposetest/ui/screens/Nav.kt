package com.example.samplecomposetest.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.samplecompose.ui.screens.ChatScreen
import com.example.samplecompose.ui.screens.ConnectScreen
import com.example.samplecompose.ui.screens.GroupsScreen
import com.example.samplecompose.ui.screens.WalletScreen
import com.example.samplecomposetest.MainActivity
import com.example.samplecomposetest.ui.screens.driverlicense.DriverLicenseCardDetails
import com.example.samplecomposetest.ui.screens.driverlicense.getDlPreviewState
import com.example.samplecomposetest.ui.screens.identity.DigitalDocuments
import com.example.samplecomposetest.ui.screens.identity.IdentityScreen


@Composable
fun GidNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = MainActivity.Screen.Identity.route, modifier) {
        composable(MainActivity.Screen.Groups.route) { GroupsScreen(navController) }
        composable(MainActivity.Screen.Chat.route) { ChatScreen(navController) }
        composable(MainActivity.Screen.Identity.route) {
            IdentityScreen(onViewAllClicked = {
                navController.navigate("${MainActivity.Screen.Identity.route}/viewAll")
            },
            onDlClicked = {
                navController.navigate("${MainActivity.Screen.Identity.route}/dl")
            })
        }
        composable("${MainActivity.Screen.Identity.route}/viewAll") {
            DigitalDocuments()
        }
        composable("${MainActivity.Screen.Identity.route}/dl") {
            DriverLicenseCardDetails(item = getDlPreviewState(),
                onBackClicked = {
                    navController.navigate(MainActivity.Screen.Identity.route)
                },
                onMoreBtnClicked = {})
    }
    composable(MainActivity.Screen.Wallet.route) { WalletScreen(navController) }
    composable(MainActivity.Screen.Connect.route) { ConnectScreen(navController) }
}
}
