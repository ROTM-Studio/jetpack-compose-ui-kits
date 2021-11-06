package com.rotmstudio.digitalbookappuikit.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rotmstudio.digitalbookappuikit.R
import com.rotmstudio.digitalbookappuikit.ui.component.DigitalBookAppUiKitTopBar
import com.rotmstudio.digitalbookappuikit.ui.dummy.WhiteListScreen
import com.rotmstudio.digitalbookappuikit.ui.home.HomeScreen
import com.rotmstudio.digitalbookappuikit.ui.navigation.BottomBarNavigation
import com.rotmstudio.digitalbookappuikit.ui.theme.Eden

@Composable
fun MainScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
) {

    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            DigitalBookAppUiKitTopBar(
                title = {
                    Text(
                        text = "Digibook",
                        color = MaterialTheme.colors.onSurface,
                        fontFamily = FontFamily(Font(R.font.nunito_bold)),
                        fontSize = 24.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    Box(
                        modifier = Modifier.size(width = 70.dp, height = 50.dp)
                    ) {
                        IconButton(onClick = { }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_menu_icon),
                                contentDescription = "",
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                    }
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .height(50.dp)
                            .padding(end = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search_icon),
                            contentDescription = "",
                        )
                    }
                },
                modifier = Modifier.drawBehind {
                    drawLine(
                        color = Eden.copy(alpha = 0.5f),
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = 2f
                    )
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = BottomBarNavigation.HomeScreen.route
        ) {
            composable(
                route = BottomBarNavigation.HomeScreen.route
            ) {
                HomeScreen()
            }
            composable(
                route = BottomBarNavigation.WhiteListScreen.route
            ) {
                WhiteListScreen()
            }
            composable(
                route = BottomBarNavigation.BagScreen.route
            ) {
                WhiteListScreen()
            }
            composable(
                route = BottomBarNavigation.ProfileScreen.route
            ) {
                WhiteListScreen()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val screen = listOf(
        BottomBarNavigation.HomeScreen,
        BottomBarNavigation.WhiteListScreen,
        BottomBarNavigation.BagScreen,
        BottomBarNavigation.ProfileScreen,
    )

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screen.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                icon = {
                    Icon(
                        painter = painterResource(id = if (currentRoute == item.route) item.selectedIcon else item.unselectedIcon),
                        contentDescription = item.title
                    )
                },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = Color.White,
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
                },
            )
        }
    }
}