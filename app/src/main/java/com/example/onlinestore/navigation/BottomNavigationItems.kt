package com.example.onlinestore.navigation


import com.example.onlinestore.R




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