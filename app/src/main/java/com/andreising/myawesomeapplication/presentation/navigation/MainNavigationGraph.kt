package com.andreising.myawesomeapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andreising.myawesomeapplication.presentation.screens.Screen
import com.andreising.myawesomeapplication.presentation.screens.menu.MainMenuScreen

@Composable
fun MainNavigationGraph(navHostController: NavHostController){
    NavHost(
        navController = navHostController, startDestination = Screen.MainMenu.route
    ) {
        composable(Screen.MainMenu.route) {
            MainMenuScreen()
        }
        composable(Screen.Profile.route) {

        }
        composable(Screen.Cart.route) {

        }
    }
}