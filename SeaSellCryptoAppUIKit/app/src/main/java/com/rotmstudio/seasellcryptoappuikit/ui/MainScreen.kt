package com.rotmstudio.seasellcryptoappuikit.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.rotmstudio.seasellcryptoappuikit.ui.component.SeaSellCryptoBottomNavigationBar
import com.rotmstudio.seasellcryptoappuikit.ui.dummy.DummyScreen
import com.rotmstudio.seasellcryptoappuikit.ui.navigation.BottomBarNavigation
import com.rotmstudio.seasellcryptoappuikit.ui.theme.Violet

@ExperimentalAnimationApi
@Composable
fun MainScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {

    val navController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                currentRoute = currentRoute
            )
        }
    ) {
        AnimatedNavHost(
            navController = navController,
            startDestination = BottomBarNavigation.DashboardScreen.route
        ) {
            composable(
                route = BottomBarNavigation.AccountScreen.route
            ) {
                DummyScreen()
            }
            composable(
                route = BottomBarNavigation.CardScreen.route
            ) {
                DummyScreen()
            }
            composable(
                route = BottomBarNavigation.DashboardScreen.route
            ) {
                DummyScreen()
            }
            composable(
                route = BottomBarNavigation.CollectionScreen.route
            ) {
                DummyScreen()
            }
            composable(
                route = BottomBarNavigation.HelpScreen.route
            ) {
                DummyScreen()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    currentRoute: String?
) {
    val screen = listOf(
        BottomBarNavigation.AccountScreen,
        BottomBarNavigation.CardScreen,
        BottomBarNavigation.DashboardScreen,
        BottomBarNavigation.CollectionScreen,
        BottomBarNavigation.HelpScreen
    )

    SeaSellCryptoBottomNavigationBar(
        backgroundColor = Violet,
        elevation = 0.dp,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        shape = RoundedCornerShape(17.dp)
    ) {
        screen.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.surface,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}