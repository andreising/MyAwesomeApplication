package com.andreising.myawesomeapplication.presentation.screens

sealed class Screen(val route: String) {
    data object MainMenu: Screen("menu")
    data object Profile: Screen("profile")
    data object Cart: Screen("cart")
}