package com.example.onlinestore.navigation

import com.example.onlinestore.R

sealed class BottomNavigationItem (val title: Int, val iconId: Int, val route: String) {
    object MainScreen1: BottomNavigationItem(R.string.bottom_menu_1, R.drawable.bottom_menu_1,"HomeScreen")
    object MainScreen2: BottomNavigationItem(R.string.bottom_menu_2, R.drawable.bottom_menu_2,"CatalogScreen")
    object MainScreen3: BottomNavigationItem(R.string.bottom_menu_3, R.drawable.bottom_menu_3,"ShopsScreen")
    object MainScreen4: BottomNavigationItem(R.string.bottom_menu_4, R.drawable.bottom_menu_4,"CartScreen")
    object MainScreen5: BottomNavigationItem(R.string.bottom_menu_5, R.drawable.bottom_menu_5,"ProfileScreen")
}