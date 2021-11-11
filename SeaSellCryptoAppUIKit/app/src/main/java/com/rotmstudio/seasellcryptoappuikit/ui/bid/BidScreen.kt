package com.rotmstudio.seasellcryptoappuikit.ui.bid

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rotmstudio.seasellcryptoappuikit.R
import com.rotmstudio.seasellcryptoappuikit.data.NftRepo
import com.rotmstudio.seasellcryptoappuikit.ui.theme.StormGray

@Composable
fun BidScreen(
    nftId: Long,
    onPlaceBidButtonTapped: () -> Unit
) {

    val nft = remember(nftId) { NftRepo.getNft(nftId = nftId) }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LazyColumn(
                contentPadding = PaddingValues(top = 48.dp, start = 48.dp, end = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Text(
                        text = nft.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colors.onSurface,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = nft.description,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light,
                        color = StormGray,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.size(48.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(28.dp),
                        elevation = 8.dp
                    ) {
                        Image(
                            painter = painterResource(id = nft.image),
                            contentDescription = nft.name,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp, start = 48.dp, end = 48.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column {
                    Text(
                        text = "Set Bid",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colors.onSurface,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                    var bid by remember { mutableStateOf(nft.bid) }

                    OutlinedTextField(
                        value = bid,
                        onValueChange = { bid = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_bitcoin),
                                contentDescription = "",
                                tint = Color.Unspecified,
                            )
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(45.dp),
                        textStyle = TextStyle.Default.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colors.onSurface
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colors.onSurface
                        )
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Button(
                        onClick = onPlaceBidButtonTapped,
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(45.dp),
                        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp)
                    ) {
                        Text(
                            text = "Place Bid",
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}