package com.rotmstudio.seasellcryptoappuikit.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

//private val DarkColorPalette = darkColors(
//    primary = Purple200,
//    primaryVariant = Purple700,
//    secondary = Teal200
//)

private val LightColorPalette = lightColors(
    primary = Canary,
    primaryVariant = Canary,
    secondary = PurpleHeart,
    secondaryVariant = PurpleHeart,
    surface = Color.White,
    background = Color.White,
    onSurface = WoodSmoke,
    onBackground = WoodSmoke,
    onPrimary = ThatchGreen,
    onSecondary = Color.White
)

@Composable
fun SeaSellCryptoAppUIKitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        LightColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}