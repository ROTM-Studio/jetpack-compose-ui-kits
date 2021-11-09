package com.rotmstudio.seasellcryptoappuikit.ui.navigation

import com.rotmstudio.seasellcryptoappuikit.R

sealed class BottomBarNavigation(
    val route: String,
    val title: String,
    val icon: Int,
) {
    object AccountScreen : BottomBarNavigation(
        "account",
        "Account",
        R.drawable.ic_outline_account_circle_24
    )

    object CardScreen : BottomBarNavigation(
        "card",
        "Card",
        R.drawable.ic_outline_credit_card_24
    )

    object DashboardScreen : BottomBarNavigation(
        "dashboard",
        "Dashboard",
        R.drawable.ic_outline_dashboard_24
    )

    object CollectionScreen : BottomBarNavigation(
        "collection",
        "Collection",
        R.drawable.ic_outline_folder_24
    )

    object HelpScreen : BottomBarNavigation(
        "help",
        "help",
        R.drawable.ic_outline_help_outline_24
    )
}