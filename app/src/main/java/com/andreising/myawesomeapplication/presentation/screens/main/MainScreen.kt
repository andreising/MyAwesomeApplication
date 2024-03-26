package com.andreising.myawesomeapplication.presentation.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.andreising.myawesomeapplication.presentation.menu.BottomNavigationMenu
import com.andreising.myawesomeapplication.presentation.navigation.MainNavigationGraph

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            BottomNavigationMenu(currentRoute = currentRoute, onNavigate = { route ->
                navController.navigate(route)
            })
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            MainNavigationGraph(
                navHostController = navController
            )
        }
    }
}