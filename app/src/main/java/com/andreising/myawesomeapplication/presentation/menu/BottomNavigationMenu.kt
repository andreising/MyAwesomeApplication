package com.andreising.myawesomeapplication.presentation.menu

import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.andreising.myawesomeapplication.presentation.theme.MAIN_COLOR

@Composable
fun BottomNavigationMenu(currentRoute: String?, onNavigate: (String) -> Unit) {
    val listItems = listOf(
        BottomNavigationItem.MenuScreenItem,
        BottomNavigationItem.ProfileScreenItem,
        BottomNavigationItem.CartScreenIcon
    )
    BottomAppBar(
        containerColor = Color.White
    ) {
        listItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    onNavigate(item.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon_id),
                        contentDescription = "bottom_icon",
                        modifier = Modifier.size(23.dp)
                    )
                },
                label = {
                    Text(text = item.title)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MAIN_COLOR,
                    unselectedIconColor = Color.Gray,
                    indicatorColor = Color.White
                )
            )

        }
    }
}