package com.rotmstudio.digitalbookappuikit.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rotmstudio.digitalbookappuikit.R
import com.rotmstudio.digitalbookappuikit.data.PopularBook
import com.rotmstudio.digitalbookappuikit.data.PopularBookRepo
import com.rotmstudio.digitalbookappuikit.ui.component.DigitalBookAppUiKitTopBar
import com.rotmstudio.digitalbookappuikit.ui.theme.*

@Composable
fun DetailScreen(
    bookId: Long,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    onBackIconTapped: () -> Unit
) {

    val book = remember(bookId) { PopularBookRepo.getPopularBook(bookId) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            DigitalBookAppUiKitTopBar(
                title = {
                    Text(
                        text = "eBook Details",
                        color = MaterialTheme.colors.onSurface,
                        fontFamily = FontFamily(Font(R.font.nunito_bold)),
                        fontSize = 24.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 20.dp),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .height(50.dp)
                    ) {
                        IconButton(onClick = onBackIconTapped) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_round_arrow_back_24),
                                contentDescription = "",
                            )
                        }
                    }
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .height(50.dp)
                            .padding(end = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_round_more_vert_24),
                            contentDescription = "",
                        )
                    }
                },
            )
        },
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp),
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 50.dp)
            ) {
                item {
                    Book(book = book)
                }
                item {
                    TitleAndPrice(book = book)
                }
                item {
                    Info(book = book)
                }
                item {
                    Description(book = book)
                }
                item {
                    Action(book = book)
                }
            }
        }
    }
}

@Composable
fun Book(
    book: PopularBook
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = book.image),
            contentDescription = book.title,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}

@Composable
fun TitleAndPrice(
    book: PopularBook
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(2f)) {
            Text(
                text = book.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = book.author,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = if (MaterialTheme.colors.isLight) Jumbo else Casper
            )
        }
        Text(
            text = "Free",
            color = MaterialTheme.colors.primary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun Info(
    book: PopularBook
) {
    Card(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = MaastrichtBlue,
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Ratings",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (MaterialTheme.colors.isLight) Jumbo else Casper
                )
                Row(
                ) {
                    Text(
                        text = book.rating.toString(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_round_star_rate_24),
                        contentDescription = "Rating",
                        tint = LightningYellow,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .padding(top = 8.dp, bottom = 8.dp)
                    .background(if (MaterialTheme.colors.isLight) Jumbo else Casper)
            )

            Column(
                modifier = Modifier.weight(2f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Number of pages",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (MaterialTheme.colors.isLight) Jumbo else Casper
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${book.numberOfPage} Page",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .padding(top = 8.dp, bottom = 8.dp)
                    .background(if (MaterialTheme.colors.isLight) Jumbo else Casper)
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Language",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (MaterialTheme.colors.isLight) Jumbo else Casper
                )
                Row(
                ) {
                    Text(
                        text = book.language,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
fun Description(
    book: PopularBook
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 24.dp)
    ) {
        Text(
            text = "Description",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = book.description,
            fontSize = 12.sp,
            color = if (MaterialTheme.colors.isLight) Jumbo else Casper,
            lineHeight = 25.sp
        )
    }
}

@Composable
fun Action(
    book: PopularBook
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .size(width = 52.dp, height = 52.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = 0.dp,
            backgroundColor = OxfordBlue
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_whitelist_solid),
                contentDescription = "White List",
                tint = if (MaterialTheme.colors.isLight) Periwinkle else Orient,
                modifier = Modifier.padding(16.dp)
            )
        }
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .padding(start = 16.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Start Reading",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Zuccini
            )
        }
    }
}