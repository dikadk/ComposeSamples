package com.example.samplecomposetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.samplecompose.ui.screens.ChatScreen
import com.example.samplecompose.ui.screens.ConnectScreen
import com.example.samplecompose.ui.screens.GroupsScreen
import com.example.samplecompose.ui.screens.WalletScreen
import com.example.samplecomposetest.ui.screens.identity.IdentityScreen
import com.example.samplecomposetest.ui.theme.GidTheme

class MainActivity : ComponentActivity() {
    private val mainScreens = listOf(
        Screen.Groups,
        Screen.Chat,
        Screen.Identity,
        Screen.Wallet,
        Screen.Connect,
    )

    sealed class Screen(val route: String, @StringRes val resourceId: Int) {
        object Groups : Screen("groups", R.string.groups)
        object Chat : Screen("groups", R.string.chat)
        object Identity : Screen("identity", R.string.identity)
        object Wallet : Screen("wallet", R.string.wallet)
        object Connect : Screen("connect", R.string.connect)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val navController = rememberNavController()

            GidTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    bottomBar = {
                        BottomNavigation {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            mainScreens.forEach { screen ->
                                BottomNavigationItem(
                                    icon = {
                                        Icon(painter = when (screen) {
                                            Screen.Chat -> painterResource(id = R.drawable.ic_chat)
                                            Screen.Connect -> painterResource(id = R.drawable.ic_connect)
                                            Screen.Groups -> painterResource(id = R.drawable.ic_groups)
                                            Screen.Identity -> painterResource(id = R.drawable.avatar)
                                            Screen.Wallet -> painterResource(id = R.drawable.ic_wallet)
                                        }, contentDescription = null)
                                    },
                                    label = { Text(stringResource(screen.resourceId)) },
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            // Pop up to the start destination of the graph to
                                            // avoid building up a large stack of destinations
                                            // on the back stack as users select items
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            // Avoid multiple copies of the same destination when
                                            // reselecting the same item
                                            launchSingleTop = true
                                            // Restore state when reselecting a previously selected item
                                            restoreState = true
                                        }
                                    },
                                    alwaysShowLabel = screen != Screen.Identity
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(navController, startDestination = Screen.Identity.route, Modifier.padding(innerPadding)) {
                        composable(Screen.Groups.route) { GroupsScreen(navController) }
                        composable(Screen.Chat.route) { ChatScreen(navController) }
                        composable(Screen.Identity.route) { IdentityScreen() }
                        composable(Screen.Wallet.route) { WalletScreen(navController) }
                        composable(Screen.Connect.route) { ConnectScreen(navController) }
                    }
                }
            }
        }
    }
}
