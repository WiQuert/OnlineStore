package com.wiquert.onlinestore.navigation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wiquert.onlinestore.R
import com.wiquert.onlinestore.screens.CartScreen
import com.wiquert.onlinestore.screens.CatalogScreen
import com.wiquert.onlinestore.screens.HomeScreen
import com.wiquert.onlinestore.screens.ProductDetailScreen
import com.wiquert.onlinestore.screens.ProfileScreen
import com.wiquert.onlinestore.screens.ShopsScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                BottomNavItems.forEach { bottomNavItem ->
                    NavigationBarItem(
                        label = {
                            Text(
                                text = stringResource(id = bottomNavItem.title),
                                fontSize = 10.sp
                            )
                        },
                        icon = {
                            Icon(
                                painterResource(id = bottomNavItem.icon),
                                contentDescription = "Icon"
                            )
                        },
                        selected = currentDestination?.hierarchy?.any { it.route == bottomNavItem.route } == true,
                        onClick = {
                            navController.navigate(bottomNavItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },

                        )
                }
            }

        })
    { innerPadding ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),
            navController = navController,
            startDestination = BottomNavItems[0].route
        ) {
            composable(BottomNavItems[0].route) {
                HomeScreen(navController)
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

            composable("product/{productId}") { backStackEntry ->
                val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull()
                if (productId != null) {
                    ProductDetailScreen(productId = productId, navController)
                } else {
                    Text("Неверный ID товара")
                }
            }
            }
        }
    }



val BottomNavItems = listOf(
    BottomNavItem(
        title = R.string.bottom_menu_1,
        route = "Home",
        icon = R.drawable.bottom_menu_1
    ),
    BottomNavItem(
        title = R.string.bottom_menu_2,
        route = "Catalog",
        icon = R.drawable.bottom_menu_2
    ),
    BottomNavItem(
        title = R.string.bottom_menu_3,
        route = "Shops",
        icon = R.drawable.bottom_menu_3,
    ),
    BottomNavItem(
        title = R.string.bottom_menu_4,
        route = "Cart",
        icon = R.drawable.bottom_menu_4
    ),
    BottomNavItem(
        title = R.string.bottom_menu_5,
        route = "Profile",
        icon = R.drawable.bottom_menu_5
    ),
)


data class BottomNavItem(
    val title: Int,
    val route: String,
    val icon: Int
)