package com.example.samplecomposetest

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentActivity
import com.example.samplecomposetest.ui.screens.driverlicense.view.DriverLicenseDetailsFragment


class MainActivity : FragmentActivity() {
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
        //view
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DriverLicenseDetailsFragment.newInstance())
                .commitNow()
        }

        //compose
        /*setContent {
            val navController = rememberNavController()
            GidTheme {
                // A surface container using the 'background' color from the theme

                Scaffold(
                    bottomBar = {
                        BottomNavigation(
                            backgroundColor = Color.White
                        ) {
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
                    GidNavHost(navController, modifier = Modifier.padding(innerPadding))
                }
            }
        }*/
    }
}
