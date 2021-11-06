package com.rotmstudio.digitalbookappuikit.ui.navigation

import com.rotmstudio.digitalbookappuikit.R

sealed class BottomBarNavigation(
    val route: String,
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int
) {
    object HomeScreen : BottomBarNavigation(
        route = "home_screen",
        title = "Home",
        selectedIcon = R.drawable.ic_home_solid,
        unselectedIcon = R.drawable.ic_home_line
    )

    object WhiteListScreen : BottomBarNavigation(
        route = "white_list_screen",
        title = "Home",
        selectedIcon = R.drawable.ic_white_list_solid,
        unselectedIcon = R.drawable.ic_whitelist_line
    )

    object BagScreen : BottomBarNavigation(
        route = "bag_screen",
        title = "Home",
        selectedIcon = R.drawable.ic_bag_solid,
        unselectedIcon = R.drawable.ic_bag_line
    )

    object ProfileScreen : BottomBarNavigation(
        route = "profile_screen",
        title = "Home",
        selectedIcon = R.drawable.ic_profile_solid,
        unselectedIcon = R.drawable.ic_profile_line
    )
}