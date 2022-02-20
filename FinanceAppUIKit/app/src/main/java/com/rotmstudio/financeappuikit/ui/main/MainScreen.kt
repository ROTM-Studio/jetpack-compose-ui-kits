package com.rotmstudio.financeappuikit.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rotmstudio.financeappuikit.R
import com.rotmstudio.financeappuikit.data.Expense
import com.rotmstudio.financeappuikit.data.ExpenseRepository
import com.rotmstudio.financeappuikit.data.expenses
import com.rotmstudio.financeappuikit.ui.component.ButtonItem
import com.rotmstudio.financeappuikit.ui.component.CustomSelectionButton
import com.rotmstudio.financeappuikit.ui.theme.*

@ExperimentalMaterialApi
@Composable
fun MainScreen() {

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed)
    )

    val expenses = remember { ExpenseRepository.getExpenses() }

    var sheetSection by remember {
        mutableStateOf(ButtonItem.EXPENSE)
    }

    BottomSheetScaffold(
        sheetContent = {
            when (sheetSection) {
                ButtonItem.EXPENSE -> ExpenseSection(expenses = expenses)
                ButtonItem.INCOME -> IncomeSection()
            }
        },
        scaffoldState = bottomSheetScaffoldState,
        backgroundColor = EbonyClay,
        sheetBackgroundColor = Color.White,
        sheetShape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
        sheetPeekHeight = 300.dp
    ) {
        Surface(
            color = EbonyClay,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ) {
                Spacer(modifier = Modifier.size(24.dp))
                MenuSection()
                Spacer(modifier = Modifier.size(32.dp))
                GreetingSection()
                Spacer(modifier = Modifier.size(24.dp))
                CardSection()
                Spacer(modifier = Modifier.size(32.dp))
                TransactionHistorySection {
                    sheetSection = it
                }
            }
        }
    }
}

@Composable
fun MenuSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            contentAlignment = Alignment.CenterStart, modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                contentAlignment = Alignment.CenterStart,
            ) {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = CharadeB,
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = "Menu",
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }
        }

        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                contentAlignment = Alignment.CenterEnd,
            ) {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = CharadeB,
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.CenterEnd,
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.profile_picture),
                            contentDescription = "Menu",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingSection() {
    Column {
        Text(
            text = "Welcome back,",
            color = SilverChalice,
            fontSize = 14.sp,
            textAlign = TextAlign.Start
        )

        Text(
            text = "Olive Mars,",
            color = Color.White,
            fontSize = 22.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun CardSection() {
    Surface(
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.ic_card),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 24.dp, start = 24.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Your Balance",
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Scorpion
                    )

                    Text(
                        text = "Rp 5.200.124",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                        color = EbonyClay
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 24.dp, start = 24.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = "4132    5123    3211    3123",
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Kabul
                )
            }
        }
    }
}

@Composable
fun TransactionHistorySection(
    sheetSection: (ButtonItem) -> Unit
) {
    Text(
        text = "Transaction History",
        color = White,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
    Spacer(modifier = Modifier.size(16.dp))
    CustomSelectionButton(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
    ) {
        sheetSection(it)
    }
}


@Composable
fun ExpenseSection(
    expenses: List<Expense>
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxHeight()
    ) {
        item {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .height(4.dp)
                        .width(60.dp)
                        .clip(RoundedCornerShape(7.dp))
                        .background(color = SpunPearl)
                )
            }
        }
        item { Spacer(modifier = Modifier.size(16.dp)) }
        items(expenses) {
            ExpenseItem(expense = it)
        }
    }
}

@Composable
fun ExpenseItem(
    expense: Expense
) {
    Surface(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
            .height(78.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Alabaster
    ) {
        Row(
            modifier = Modifier
                .height(78.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .size(50.dp)
                    .padding(start = 16.dp)
            ) {
                Card(
                    modifier = Modifier.size(50.dp),
                    backgroundColor = if (expense.image == R.drawable.hokben) {
                        LightningYellow
                    } else {
                        Concrete
                    },
                    elevation = 0.dp,
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Image(
                        painter = painterResource(id = expense.image),
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                            .wrapContentSize()
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
            ) {
                Text(
                    text = expense.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = EbonyClay
                )
                Text(
                    text = expense.amount,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = Persimmon
                )
            }

            Text(
                text = expense.time,
                fontSize = 12.sp,
                color = Venus,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentSize()
                    .padding(end = 16.dp),
                textAlign = TextAlign.Right
            )
        }
    }
}

@Composable
fun IncomeSection() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "Coming soon!",
            textAlign = TextAlign.Center,
            modifier = Modifier.wrapContentSize(),
            color = EbonyClay,
            fontWeight = FontWeight.SemiBold
        )
    }
}













