package com.wiquert.onlinestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.wiquert.onlinestore.navigation.BottomNavigation
import com.wiquert.onlinestore.ui.theme.OnlineStoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnlineStoreTheme(dynamicColor = false) {
                BottomNavigation()
            }
        }
    }
}