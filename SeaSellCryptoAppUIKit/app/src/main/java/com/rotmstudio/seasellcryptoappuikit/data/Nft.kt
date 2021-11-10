package com.rotmstudio.seasellcryptoappuikit.data

import com.rotmstudio.seasellcryptoappuikit.R

data class Nft(
    val id: Long,
    val isTrending: Boolean,
    val favorite: Boolean,
    val image: Int,
    val name: String,
    val description: String
)

val nfts = listOf(
    Nft(
        id = 1L,
        isTrending = false,
        favorite = false,
        image = R.drawable.image_1,
        name = "Fast Food",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer varius cursus placerat."
    ),
    Nft(
        id = 2L,
        isTrending = false,
        favorite = false,
        image = R.drawable.image_2,
        name = "Ethereum",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer varius cursus placerat."
    ),
    Nft(
        id = 3L,
        isTrending = false,
        favorite = false,
        image = R.drawable.image_3,
        name = "Dark Game",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer varius cursus placerat."
    ),
    Nft(
        id = 4L,
        isTrending = false,
        favorite = false,
        image = R.drawable.image_4,
        name = "Warehouse",
        description = "Menggambarkan betapa pentingnya sebuah persediaan"
    )
)
