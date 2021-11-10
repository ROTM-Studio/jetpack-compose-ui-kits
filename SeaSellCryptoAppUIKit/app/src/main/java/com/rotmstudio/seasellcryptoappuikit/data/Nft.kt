package com.rotmstudio.seasellcryptoappuikit.data

import com.rotmstudio.seasellcryptoappuikit.R

data class Nft(
    val id: Long,
    val isTrending: Boolean,
    val isFavorite: Boolean,
    val image: Int,
    val name: String,
    val description: String
)

val nfts = listOf(
    Nft(
        id = 1L,
        isTrending = false,
        isFavorite = true,
        image = R.drawable.image_1,
        name = "Fast Food",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer varius cursus placerat."
    ),
    Nft(
        id = 2L,
        isTrending = true,
        isFavorite = false,
        image = R.drawable.image_2,
        name = "Ethereum",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer varius cursus placerat."
    ),
    Nft(
        id = 3L,
        isTrending = false,
        isFavorite = true,
        image = R.drawable.image_3,
        name = "Dark Game",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer varius cursus placerat."
    ),
    Nft(
        id = 4L,
        isTrending = true,
        isFavorite = false,
        image = R.drawable.image_4,
        name = "Warehouse",
        description = "Menggambarkan betapa pentingnya sebuah persediaan"
    )
)
