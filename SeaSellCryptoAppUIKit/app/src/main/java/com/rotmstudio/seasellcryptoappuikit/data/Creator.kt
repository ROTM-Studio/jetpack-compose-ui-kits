package com.rotmstudio.seasellcryptoappuikit.data

import com.rotmstudio.seasellcryptoappuikit.R

data class Creator(
    val image: Int,
    val isSeen: Boolean = false
)

val popularCreators = listOf(
    Creator(R.drawable.user_1, true),
    Creator(R.drawable.user_2),
    Creator(R.drawable.user_3),
    Creator(R.drawable.user_4, true),
    Creator(R.drawable.user_5),
    Creator(R.drawable.user_6)
)
