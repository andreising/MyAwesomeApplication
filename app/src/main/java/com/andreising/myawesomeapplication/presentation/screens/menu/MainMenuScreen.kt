package com.andreising.myawesomeapplication.presentation.screens.menu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import com.andreising.myawesomeapplication.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.andreising.myawesomeapplication.domain.model.BurgerItem
import com.andreising.myawesomeapplication.domain.util.Resource
import com.andreising.myawesomeapplication.presentation.theme.MAIN_COLOR
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun MainMenuScreen() {
    val viewModel: MainMenuScreenViewModel = hiltViewModel()
    val dataFlow = viewModel.data.collectAsState(initial = Resource.Loading<List<BurgerItem>>())
    when (val data = dataFlow.value) {
        is Resource.Loading -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(color = MAIN_COLOR)
            }
        }

        is Resource.Success -> {
            data.data?.let {
                MainContent(burgerList = data.data, withError = false)
            }
        }

        is Resource.Error -> {
            data.data?.let {
                MainContent(burgerList = data.data, withError = true)
            }
        }
    }

}

@Composable
fun MainContent(burgerList: List<BurgerItem>, withError: Boolean) {
    val state = rememberCollapsingToolbarScaffoldState()
    val choseCategory = remember {
        mutableStateOf("No filters")
    }
    val filteredList = when (val parameter = choseCategory.value) {
        "No filters" -> burgerList
        else -> burgerList.filter { it.name.lowercase().contains(parameter.lowercase()) }
    }
    CollapsingToolbarScaffold(modifier = Modifier.fillMaxSize(),
        state = state,
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {

            val categoryList = listOf(
                "No filters", "Egg", "Sausage", "Cheese", "Chicken"
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
                    .parallax(1f),
                contentAlignment = Alignment.BottomCenter
            ) {
                Carousel()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White.copy(1 - state.toolbarState.progress))
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                DropdownAndQrCode(withError)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .parallax(1f),
                contentAlignment = Alignment.BottomCenter
            ) {
                Row(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(16.dp)
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .road(Alignment.Center, Alignment.BottomStart)
                ) {
                    categoryList.forEach {
                        Card(colors = CardDefaults.cardColors(
                            containerColor = if (it == choseCategory.value) MAIN_COLOR.copy(
                                alpha = 0.2f
                            )
                            else Color.White
                        ), modifier = Modifier
                            .clickable {
                                choseCategory.value = it
                            }
                            .padding(horizontal = 6.dp),
                            shape = RoundedCornerShape(6.dp)) {
                            Text(
                                text = it,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                                color = if (it == choseCategory.value) MAIN_COLOR
                                else Color.Gray
                            )
                        }
                    }
                }
            }
        }) {
        LazyColumn(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
        ) {
            items(filteredList) { burger ->
                BurgerUIItem(burgerItem = burger)
            }
        }
    }

}

@Composable
fun Carousel() {
    val scrollState = rememberScrollState()
    val itemList = listOf(
        R.drawable.carousel_item_1, R.drawable.carousel_item_2, R.drawable.carousel_item_3
    )
    Row(
        modifier = Modifier
            .padding(start = 16.dp)
            .fillMaxWidth()
            .height(120.dp)
            .horizontalScroll(scrollState)
    ) {
        itemList.forEach {
            Card(modifier = Modifier.padding(8.dp)) {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(300.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
fun BurgerUIItem(burgerItem: BurgerItem) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .height(120.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = burgerItem.image,
            contentDescription = "img_burger",
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier
                .weight(2f)
                .fillMaxHeight()
                .padding(start = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = burgerItem.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(
                text = burgerItem.desc,
                fontSize = 14.sp,
                color = Color.LightGray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                Card(
                    border = BorderStroke(1.dp, MAIN_COLOR)
                ) {
                    Text(
                        text = "от ${burgerItem.price} \$",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                        color = MAIN_COLOR
                    )
                }
            }
        }
    }
}

@Composable
fun DropdownAndQrCode(withError: Boolean) {
    var expandedDropdown by remember { mutableStateOf(false) }
    var currentCity by remember { mutableStateOf("Москва") }
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = currentCity)
                IconButton(onClick = { expandedDropdown = !expandedDropdown }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "More"
                    )
                }
            }
            DropdownMenu(expanded = expandedDropdown,
                onDismissRequest = { expandedDropdown = false }) {
                DropdownMenuItem(text = { Text(text = "Москва") }, onClick = {
                    currentCity = "Москва"
                    expandedDropdown = false
                })
                DropdownMenuItem(text = { Text(text = "СПБ") }, onClick = {
                    currentCity = "СПБ"
                    expandedDropdown = false
                })
                DropdownMenuItem(text = { Text(text = "Самара") }, onClick = {
                    currentCity = "Самара"
                    expandedDropdown = false
                })
            }
        }
        if (!withError) IconButton(onClick = { }) {
            Icon(
                painter = painterResource(id = R.drawable.qr_code),
                contentDescription = "More"
            )
        }
        else IconButton(onClick = { }) {
            Image(
                painter = painterResource(id = R.drawable.no_internet),
                contentDescription = "More"
            )
        }
    }
}