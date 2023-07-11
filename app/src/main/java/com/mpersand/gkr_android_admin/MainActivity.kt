package com.mpersand.gkr_android_admin

import android.graphics.Color
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.mpersand.presentation.view.auth.navigation.signInRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setTransparentStatusBar()
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                GKRAdminNavHost(navController = rememberNavController(), startDestination = signInRoute)
            }
        }
    }
}

fun Window.setTransparentStatusBar() {
    WindowCompat.setDecorFitsSystemWindows(this, false)
    val insetsController = WindowCompat.getInsetsController(this, this.decorView)
    insetsController.isAppearanceLightStatusBars = true
    statusBarColor = Color.TRANSPARENT
}