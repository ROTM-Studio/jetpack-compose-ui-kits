package com.rotmstudio.seasellcryptoappuikit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rotmstudio.seasellcryptoappuikit.ui.theme.SeaSellCryptoAppUIKitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SeaSellCryptoAppUIKitTheme {
                val systemUiController = rememberSystemUiController()
                val statusBarAndNavigationBarColor = MaterialTheme.colors.surface
                val useDarkIcons = MaterialTheme.colors.isLight

                SideEffect {
                    systemUiController.apply {
                        setStatusBarColor(
                            color = statusBarAndNavigationBarColor,
                            darkIcons = useDarkIcons
                        )
                        setNavigationBarColor(
                            color = statusBarAndNavigationBarColor,
                            darkIcons = useDarkIcons
                        )
                    }
                }
                ProvideWindowInsets {

                }
            }
        }
    }
}