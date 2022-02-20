package com.rotmstudio.financeappuikit.data

import com.rotmstudio.financeappuikit.R

data class Expense(
    val name: String,
    val amount: String,
    val image: Int,
    val time: String
)

val expenses = listOf(
    Expense(
        name = "McDonald's",
        amount = "- Rp124.021",
        image = R.drawable.ic_mcd,
        time = "13:01 am"
    ),
    Expense(
        name = "Hoka Ben",
        amount = "- Rp200.000",
        image = R.drawable.hokben,
        time = "Yesterday"
    ),
    Expense(
        name = "KFC",
        amount = "- Rp200.000",
        image = R.drawable.ic_kfc_4_1,
        time = "Yesterday"
    ),
    Expense(
        name = "McDonald's",
        amount = "- Rp124.021",
        image = R.drawable.ic_mcd,
        time = "Yesterday"
    ),
)
