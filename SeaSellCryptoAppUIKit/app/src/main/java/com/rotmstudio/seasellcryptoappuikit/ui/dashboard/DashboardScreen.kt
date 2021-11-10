package com.rotmstudio.seasellcryptoappuikit.ui.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.rotmstudio.seasellcryptoappuikit.R
import com.rotmstudio.seasellcryptoappuikit.data.CreatorRepo
import com.rotmstudio.seasellcryptoappuikit.data.NftRepo
import com.rotmstudio.seasellcryptoappuikit.ui.theme.Cerise
import com.rotmstudio.seasellcryptoappuikit.ui.theme.PurpleHeart
import com.rotmstudio.seasellcryptoappuikit.ui.theme.SantasGray
import com.rotmstudio.seasellcryptoappuikit.ui.theme.StormGray
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun DashboardScreen(
    onPagerItemTapped: (nftId: Long) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(top = 24.dp, bottom = 100.dp)
        ) {
            item {
                TopBar()
            }
            item {
                Spacer(modifier = Modifier.size(38.dp))
            }
            item {
                NftPager {
                    onPagerItemTapped(it)
                }
            }
            item {
                Spacer(modifier = Modifier.size(38.dp))
            }
            item {
                PopularCreator()
            }
            item {
                Spacer(modifier = Modifier.size(38.dp))
            }
            item {
                MyRecentBids()
            }
        }
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "SeaSell",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = "Find Your NFTs Today",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                color = StormGray
            )
        }
        Image(
            painter = painterResource(id = R.drawable.bag_icon),
            contentDescription = "bag",
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Image(
            painter = painterResource(id = R.drawable.notification_icon),
            contentDescription = "bag",
            modifier = Modifier.size(24.dp)
        )
    }
}

@ExperimentalPagerApi
@Composable
fun NftPager(
    onPagerItemTapped: (nftId: Long) -> Unit
) {

    val nfts = remember { NftRepo.getNfts() }

    HorizontalPager(
        count = nfts.size,
        contentPadding = PaddingValues(horizontal = 60.dp),
    ) { page ->
        Card(
            Modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                }
                .width(270.dp)
                .height(340.dp),
            shape = RoundedCornerShape(28.dp),
            elevation = 8.dp
        ) {
            Box {
                Image(
                    painter = painterResource(id = nfts[page].image),
                    contentDescription = nfts[page].name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                if (nfts[page].isTrending) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Card(
                            backgroundColor = MaterialTheme.colors.secondary,
                            shape = RoundedCornerShape(9.dp),
                            modifier = Modifier
                                .size(height = 30.dp, width = 100.dp),
                            elevation = 0.dp
                        ) {
                            Text(
                                text = "Trending",
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colors.onSecondary,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                onPagerItemTapped(nfts[page].id)
                            },
                            modifier = Modifier
                                .width(176.dp)
                                .height(40.dp),
                            shape = RoundedCornerShape(45.dp)
                        ) {
                            Text(text = "Bid Now", fontWeight = FontWeight.Medium, fontSize = 16.sp)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .drawBehind {
                                    drawArc(
                                        color = Color.White,
                                        startAngle = 0f,
                                        sweepAngle = 360f,
                                        useCenter = true
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = if (nfts[page].isFavorite) R.drawable.ic_round_favorite_24 else R.drawable.ic_round_favorite_border_24),
                                contentDescription = "",
                                tint = MaterialTheme.colors.secondary,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PopularCreator() {

    val popularCreators = remember { CreatorRepo.getPopularCreators() }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Popular Creators",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 16.dp),
            color = MaterialTheme.colors.onSurface
        )
        LazyRow(contentPadding = PaddingValues(start = 16.dp, top = 16.dp)) {
            items(popularCreators) { creator ->
                Column(
                    modifier = Modifier
                        .size(60.dp)
                        .drawBehind {
                            if (creator.isSeen) {
                                drawArc(
                                    color = SantasGray,
                                    startAngle = 0f,
                                    sweepAngle = 360f,
                                    useCenter = false,
                                    style = Stroke(
                                        width = 5f
                                    )
                                )
                            } else {
                                drawArc(
                                    brush = Brush.linearGradient(
                                        colors = listOf(
                                            PurpleHeart,
                                            Cerise
                                        ),
                                        start = Offset.Zero,
                                        end = Offset(130f, 230f)
                                    ),
                                    startAngle = 0f,
                                    sweepAngle = 360f,
                                    useCenter = false,
                                    style = Stroke(
                                        width = 5f
                                    )
                                )
                            }

                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = creator.image),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(CircleShape)
                    )
                }
                Spacer(modifier = Modifier.width(15.3.dp))
            }
        }
    }
}

@Composable
fun MyRecentBids() {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        Text(
            text = "My Recent Bids",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.size(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.image_5),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(16.dp)),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(2f)) {
                Text(
                    text = "Dart Celline",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    text = "Apr 22",
                    fontSize = 12.sp,
                    color = StormGray
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_bitcoin),
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "28.40",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.onSurface
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.image_6),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(16.dp)),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(2f)) {
                Text(
                    text = "Zipzip Koin",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    text = "Feb 31",
                    fontSize = 12.sp,
                    color = StormGray
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_bitcoin),
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "1.10",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.onSurface
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.image_7),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(16.dp)),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(2f)) {
                Text(
                    text = "Dart Celline",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    text = "Feb 9",
                    fontSize = 12.sp,
                    color = StormGray
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_bitcoin),
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "590.00",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.onSurface
            )
        }
    }
}