package com.example.onlinestore.screens

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.onlinestore.navigation.BottomNavItems

@Composable
fun Home(modifier: Modifier = Modifier) {

    val selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
    bottomBar = {
        NavigationBar {
            BottomNavItems.forEachIndexed { index, navItem ->
                NavigationBarItem(
                    selected = index == selectedIndex,
                    onClick = {
                        index == selectedIndex
                        navController.navigate(bottomNavItem.route)
                    },
                    icon = {
                        Icon(painterResource(id = navItem.icon), contentDescription = "Icon")
                    },
                    label = {
                        Text(text = stringResource(id = navItem.title))
                    }
                )
            }
        }
    })
    {
        Text(text = "Hi")
    }
}