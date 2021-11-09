package com.rotmstudio.seasellcryptoappuikit.ui.navigation

sealed class Navigation(val route: String) {
    object BidScreen : Navigation("bid_screen") {

        const val ROUTE_WITH_ARGUMENT: String = "bid_screen/{NFT_ID}"

        const val NFT_ID: String = "NFT_ID"
    }
}