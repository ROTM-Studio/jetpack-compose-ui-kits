package com.rotmstudio.seasellcryptoappuikit.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rotmstudio.seasellcryptoappuikit.ui.bid.BidScreen
import com.rotmstudio.seasellcryptoappuikit.ui.component.SeaSellCryptoBottomNavigationBar
import com.rotmstudio.seasellcryptoappuikit.ui.dashboard.DashboardScreen
import com.rotmstudio.seasellcryptoappuikit.ui.dummy.DummyScreen
import com.rotmstudio.seasellcryptoappuikit.ui.navigation.BottomBarNavigation
import com.rotmstudio.seasellcryptoappuikit.ui.navigation.Navigation
import com.rotmstudio.seasellcryptoappuikit.ui.theme.Violet

@ExperimentalPagerApi
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
            if (currentRoute != Navigation.BidScreen.ROUTE_WITH_ARGUMENT) {
                val state = remember {
                    MutableTransitionState(false).apply {
                        targetState = true
                    }
                }
                AnimatedVisibility(
                    visibleState = state,
                    enter = slideInVertically(
                        initialOffsetY = { -40 },
                        animationSpec = tween(durationMillis = 1000)
                    ) + fadeIn(
                        initialAlpha = 0.3f, animationSpec = tween(durationMillis = 1000)
                    ),
                    exit = fadeOut()
                ) {
                    BottomNavigationBar(
                        navController = navController,
                        currentRoute = currentRoute
                    )
                }
            }
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
                DashboardScreen(
                    onPagerItemTapped = {
                        navController.navigate("bid_screen/$it")
                    }
                )
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
            composable(
                route = Navigation.BidScreen.ROUTE_WITH_ARGUMENT,
                arguments = listOf(
                    navArgument(Navigation.BidScreen.NFT_ID) {
                        type = NavType.LongType
                    }
                )
            ) {
                val nftId = it.arguments?.getLong(Navigation.BidScreen.NFT_ID)
                    ?: return@composable

                BidScreen(
                    nftId = nftId,
                    onPlaceBidButtonTapped = { navController.popBackStack() }
                )
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