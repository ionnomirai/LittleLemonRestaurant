package com.examplecafe.littlelemon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.examplecafe.littlelemon.auxiliary.*
import com.examplecafe.littlelemon.ui.theme.LittleLemonColor
import com.examplecafe.littlelemon.ui.theme.Typography

@Composable
fun Home(navController: NavController, items: List<MenuItemRoom>) {

    var filterData by remember {
        mutableStateOf("")
    }

    val searchPhrase = rememberSaveable {
        mutableStateOf("")
    }

    var elFromDB = items

    when (filterData) {
        "Starters" -> elFromDB = elFromDB.filter { cur -> cur.category == "starters" }
        "Mains" -> elFromDB = elFromDB.filter { cur -> cur.category == "mains" }
        "Desserts" -> elFromDB = elFromDB.filter { cur -> cur.category == "desserts" }
        "All" -> elFromDB = elFromDB
    }

    val listFilter: List<String> = mutableListOf(
        "Starters", "Mains", "Desserts", "All"
    )

    if (!searchPhrase.value.isEmpty()) {
        elFromDB = elFromDB.filter { it.title.contains(searchPhrase.value, true) }
    }

    @Composable
    fun FilterButtonsDisplay(filterType: String) {
        Button(
            onClick = {
                filterData = filterType
            },
            modifier = Modifier
                .padding(horizontal = 10.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = LittleLemonColor.green),
            shape = RoundedCornerShape(20)
        ) {
            Text(text = filterType, style = Typography.subtitle1)
        }
    }

    Scaffold(topBar = { TopBar(navController) }) {
        Column() {

            LazyColumn() {
                item {
                    UpperPanel().UpperField(
                        name = stringResource(id = R.string.Home_panel_title),
                        city = stringResource(id = R.string.Home_panel_chicago),
                        descptionOne = stringResource(id = R.string.Home_panel_descriptionOne),
                        idImg = R.drawable.little_lemon1
                    )
                }

                //field for search
                item {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlineInputLL(
                            searchPhrase,
                            "Search",
                            "Example: Salad",
                            Icons.Default.Search,
                            "Search Icon",
                            paddingStart = 10, paddingEnd = 10
                        )
                    }
                }

                //buttons for filter
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(
                            items = listFilter,
                            itemContent = { filtType ->
                                FilterButtonsDisplay(filtType)
                            },
                        )
                    }
                }

                //output dishes, what are save in DB
                items(
                    items = elFromDB,
                    itemContent = { menuItem ->
                        ItemDisplay(menuItem = menuItem, navController = navController)
                    },
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemDisplay(menuItem: MenuItemRoom, navController: NavController) {

    Card {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                //moving information from dish item in special object SingleMenuDishScreen
                transportTempDish.id = menuItem.id
                transportTempDish.title = menuItem.title
                transportTempDish.price = menuItem.price
                transportTempDish.image = menuItem.image
                transportTempDish.description = menuItem.description
                transportTempDish.category = menuItem.category
                transportTempDish.count = 0

                navController.navigate(SingleMenuDishDes.route) {
                    launchSingleTop = true
                }
            }
        ) {
            //visual output information about dish
            Column() {
                Text(
                    text = menuItem.title,
                    style = Typography.body1,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 5.dp)
                )
                Text(
                    text = menuItem.description,
                    color = Color.Gray,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(.71f)
                )
                Text(
                    text = "${menuItem.price.toString()}$",
                    style = Typography.body2,
                    color = LittleLemonColor.green,
                    modifier = Modifier
                        .padding(5.dp)
                )
            }
            Column(modifier = Modifier.align(CenterVertically)) {
                GlideImage(
                    model = menuItem.image,
                    contentDescription = menuItem.title,
                    modifier = Modifier
                        .height(100.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .padding(horizontal = 5.dp),
                    contentScale = ContentScale.FillHeight
                )
            }
        }
        HorizontalDivider()
    }
}