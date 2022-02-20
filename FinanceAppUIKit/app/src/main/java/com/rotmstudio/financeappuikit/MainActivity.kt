package com.rotmstudio.financeappuikit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rotmstudio.financeappuikit.ui.main.MainScreen
import com.rotmstudio.financeappuikit.ui.theme.EbonyClay
import com.rotmstudio.financeappuikit.ui.theme.FinanceAppUIKitTheme

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinanceAppUIKitTheme {
                val systemUiController = rememberSystemUiController()
                val statusBarColor = EbonyClay
                val navigationBarColor = Color.White

                SideEffect {
                    systemUiController.apply {
                        setStatusBarColor(
                            statusBarColor,
                            darkIcons = false
                        )
                        setNavigationBarColor(
                            navigationBarColor,
                            darkIcons = true
                        )
                    }
                }

                ProvideWindowInsets {
                    MainScreen()
                }
            }
        }
    }
}