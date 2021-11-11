package com.rotmstudio.seasellcryptoappuikit.data

import com.rotmstudio.seasellcryptoappuikit.R

data class RecentBid(
    val nftId: Long,
    val name: String,
    val date: String,
    val image: Int,
    val bid: String
)

val recentBids = listOf(
    RecentBid(
        nftId = 1L,
        name = "Dart Celline",
        date = "Apr 22",
        image = R.drawable.image_5,
        bid = "28.40"
    ),
    RecentBid(
        nftId = 2L,
        name = "Zipzip Koin",
        date = "Feb 31",
        image = R.drawable.image_6,
        bid = "1.10"
    ),
    RecentBid(
        nftId = 3L,
        name = "Dart Celline",
        date = "Feb 9",
        image = R.drawable.image_7,
        bid = "590.00"
    )
)
