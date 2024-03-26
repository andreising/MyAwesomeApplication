package com.andreising.myawesomeapplication.presentation.menu

import com.andreising.myawesomeapplication.R
import com.andreising.myawesomeapplication.presentation.screens.Screen

sealed class BottomNavigationItem(val icon_id: Int, val route: String, val title: String) {
    data object MenuScreenItem :
        BottomNavigationItem(R.drawable.menu_icon, Screen.MainMenu.route, "Меню")

    data object ProfileScreenItem :
        BottomNavigationItem(R.drawable.profile_icon, Screen.Profile.route, "Профиль")

    data object CartScreenIcon :
        BottomNavigationItem(R.drawable.cart_icon, Screen.Cart.route, "Корзина")
}