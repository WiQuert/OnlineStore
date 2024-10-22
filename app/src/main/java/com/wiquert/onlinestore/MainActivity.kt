package com.wiquert.onlinestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wiquert.onlinestore.navigation.BottomNavItems
import com.wiquert.onlinestore.screens.CartScreen
import com.wiquert.onlinestore.screens.CatalogScreen
import com.wiquert.onlinestore.screens.HomeScreen
import com.wiquert.onlinestore.screens.ProfileScreen
import com.wiquert.onlinestore.screens.ShopsScreen
import com.wiquert.onlinestore.ui.theme.OnlineStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnlineStoreTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        NavigationBar {

                                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                                    val currentDestination = navBackStackEntry?.destination

                                    BottomNavItems.forEach { bottomNavItem ->
                                        NavigationBarItem(
                                            label = {
                                                Text(text = stringResource(id = bottomNavItem.title))
                                            },
                                            icon = {
                                                Icon(
                                                    painterResource(id = bottomNavItem.icon),
                                                    contentDescription = "Icon"
                                                )
                                            },
                                            selected = currentDestination?.hierarchy?.any { it.route == bottomNavItem.route} == true,
                                            onClick = { navController.navigate(bottomNavItem.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            } },

                                            )
                                    }
                                }

                            })
                        {   innerPadding ->
                            NavHost(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding),
                                navController = navController,
                                startDestination = BottomNavItems[0].route
                            ) {
                                composable(BottomNavItems[0].route) {
                                    HomeScreen()
                                }
                                composable(BottomNavItems[1].route) {
                                    CatalogScreen()
                                }
                                composable(BottomNavItems[2].route) {
                                    ShopsScreen()
                                }
                                composable(BottomNavItems[3].route) {
                                    CartScreen()
                                }
                                composable(BottomNavItems[4].route) {
                                    ProfileScreen()
                                }
                            }
                        }
            }
        }
    }
}
