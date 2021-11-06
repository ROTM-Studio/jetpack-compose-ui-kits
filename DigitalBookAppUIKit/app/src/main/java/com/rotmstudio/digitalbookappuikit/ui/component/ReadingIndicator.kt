package com.rotmstudio.digitalbookappuikit.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import com.rotmstudio.digitalbookappuikit.ui.theme.Casper
import com.rotmstudio.digitalbookappuikit.ui.theme.Eden
import com.rotmstudio.digitalbookappuikit.ui.theme.Shamrock

@Composable
fun ReadingIndicator(
    lastPage: Float,
    totalPage: Float,
    modifier: Modifier = Modifier
) {
    val color = if (MaterialTheme.colors.isLight) Casper else Eden

    var width by remember {
        mutableStateOf(0f)
    }

    val anim by animateFloatAsState(
        targetValue = width * (lastPage / totalPage),
        tween(durationMillis = 2000)
    )

    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .drawBehind {
                    width = size.width
                    drawLine(
                        color = color,
                        start = Offset.Zero,
                        end = Offset(size.width, 0f),
                        cap = StrokeCap.Round,
                        strokeWidth = 20f
                    )
                }
                .drawBehind {
                    drawLine(
                        color = Shamrock,
                        start = Offset.Zero,
                        end = Offset(anim, 0f),
                        cap = StrokeCap.Round,
                        strokeWidth = 20f
                    )
                }
        )
    }
}