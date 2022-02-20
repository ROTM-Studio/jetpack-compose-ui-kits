package com.rotmstudio.financeappuikit.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rotmstudio.financeappuikit.ui.theme.Charade
import com.rotmstudio.financeappuikit.ui.theme.EbonyClay
import com.rotmstudio.financeappuikit.ui.theme.White

enum class ButtonItem(val title: String) {
    EXPENSE("Expense"),
    INCOME("Income")
}

object ClearRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor(): Color = Color.Transparent

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(
        draggedAlpha = 0.0f,
        focusedAlpha = 0.0f,
        hoveredAlpha = 0.0f,
        pressedAlpha = 0.0f
    )
}

@Composable
fun CustomSelectionButton(
    modifier: Modifier = Modifier,
    selected: (ButtonItem) -> Unit
) {
    val options = listOf(
        ButtonItem.EXPENSE,
        ButtonItem.INCOME
    )

    var selectedOption by remember {
        mutableStateOf(0)
    }

    val onSelectionChange = { index: Int, buttonItem: ButtonItem ->
        selectedOption = index
        selected(buttonItem)
    }

    Surface(
        shape = RoundedCornerShape(40.dp),
        color = Charade,
        modifier = Modifier
            .fillMaxWidth()
            .height(43.dp)
    ) {
        CompositionLocalProvider(LocalRippleTheme provides ClearRippleTheme) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                options.forEachIndexed { index, buttonItem ->
                    SelectionButtonItem(
                        isSelected = selectedOption == index,
                        onClick = { onSelectionChange(index, buttonItem) },
                        title = buttonItem.title
                    )
                }
            }
        }
    }
}

@Composable
private fun RowScope.SelectionButtonItem(
    isSelected: Boolean,
    onClick: () -> Unit,
    title: String
) {
    val color by animateColorAsState(
        if (isSelected) White else Color.Transparent
    )

    val textColor by animateColorAsState(
        if (isSelected) EbonyClay else Color.White,
        tween(durationMillis = 500, easing = FastOutLinearInEasing)
    )

    Box(
        modifier = Modifier
            .weight(1f)
            .clickable { onClick() }
    ) {
        Surface(
            color = color,
            shape = RoundedCornerShape(35.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = title,
                color = textColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.wrapContentHeight(),
                fontWeight = if (isSelected) {
                    FontWeight.SemiBold
                } else {
                    FontWeight.Medium
                },
                fontSize = 12.sp
            )
        }
    }
}












