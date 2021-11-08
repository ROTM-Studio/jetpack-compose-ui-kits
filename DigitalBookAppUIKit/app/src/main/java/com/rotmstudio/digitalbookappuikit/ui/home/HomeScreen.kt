package com.rotmstudio.digitalbookappuikit.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rotmstudio.digitalbookappuikit.R
import com.rotmstudio.digitalbookappuikit.data.PopularBookRepo
import com.rotmstudio.digitalbookappuikit.ui.component.ReadingIndicator
import com.rotmstudio.digitalbookappuikit.ui.theme.Casper
import com.rotmstudio.digitalbookappuikit.ui.theme.Jumbo
import com.rotmstudio.digitalbookappuikit.ui.theme.LightningYellow

@Composable
fun HomeScreen(
    onBookTapped: (bookId: Long) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f),
        shape = RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp),
        elevation = 0.dp,
    ) {
        LazyColumn(
            contentPadding = PaddingValues(top = 32.dp, bottom = 50.dp)
        ) {
            item {
                RecentBook()
                PopularBook { onBookTapped(it) }
                RecommendedBook()
            }
        }
    }
}

@Composable
fun RecentBook() {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(200.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Recent Book",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "View all",
                fontSize = 12.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(1f),
                color = if (MaterialTheme.colors.isLight) Jumbo else Casper
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.atomic_habits),
                    contentDescription = "",
                    modifier = Modifier.clip(RoundedCornerShape(4.dp))
                )
                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text(
                        text = "Atomic Habits",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Text(
                        text = "James Clear",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (MaterialTheme.colors.isLight) Jumbo else Casper,
                    )
                    val lastPage = 178f
                    val totalPage = 356f
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(2f),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = "Pages ${lastPage.toInt()}/${totalPage.toInt()}",
                            fontSize = 10.sp,
                            color = if (MaterialTheme.colors.isLight) Jumbo else Casper,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "${(lastPage / totalPage * 100).toInt()}%",
                            fontSize = 10.sp,
                            color = if (MaterialTheme.colors.isLight) Jumbo else Casper,
                            textAlign = TextAlign.End,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    ReadingIndicator(
                        lastPage = lastPage,
                        totalPage = totalPage,
                        modifier = Modifier.padding(bottom = 8.dp, start = 4.dp, end = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun PopularBook(
    onBookTapped: (bookId: Long) -> Unit
) {

    val popularBooks = remember { PopularBookRepo.getPopularBooks() }

    Column(
        modifier = Modifier
            .padding(top = 32.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = "Popular Books",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "View all",
                fontSize = 12.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(1f),
                color = if (MaterialTheme.colors.isLight) Jumbo else Casper
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        LazyRow(contentPadding = PaddingValues(start = 16.dp)) {
            items(popularBooks) { book ->
                Box(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .height(280.dp)
                        .width(150.dp)
                        .clickable {
                            onBookTapped(book.id)
                        }
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = book.image),
                            contentDescription = book.title,
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                        )
                        Text(
                            text = book.title,
                            fontSize = 16.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(top = 16.dp)
                        )
                        Text(
                            text = book.author,
                            fontSize = 12.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = if (MaterialTheme.colors.isLight) Jumbo else Casper,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RecommendedBook() {
    Column(
        modifier = Modifier
            .padding(top = 32.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Recommended",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "View all",
                fontSize = 12.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(1f),
                color = if (MaterialTheme.colors.isLight) Jumbo else Casper
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.sebuah_seni_untuk_bersikap_bodo_amat),
                contentDescription = "Sebuah seni untuk bersikap bodo amat",
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = "Sebuah Seni untuk Bersikap Bodo Amat",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Mark Manson",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (MaterialTheme.colors.isLight) Jumbo else Casper,
                )
                Row(
                    modifier = Modifier.padding(top = 32.dp)
                ) {
                    for (i in 1..5) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_round_star_rate_24),
                            contentDescription = "Rating",
                            tint = LightningYellow
                        )
                    }
                }
            }
        }
    }
}