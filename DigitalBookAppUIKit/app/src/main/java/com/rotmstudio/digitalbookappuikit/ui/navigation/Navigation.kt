package com.rotmstudio.digitalbookappuikit.ui.navigation

sealed class Navigation(val route: String) {
    object DetailScreen : Navigation("detail_screen") {

        const val ROUTE_WITH_ARGUMENT: String = "detail_screen/{BOOK_ID}"

        const val BOOK_ID: String = "BOOK_ID"
    }
}