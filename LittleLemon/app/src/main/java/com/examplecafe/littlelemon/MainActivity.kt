package com.examplecafe.littlelemon

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.examplecafe.littlelemon.registration.RegistrationScreen
import com.examplecafe.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import androidx.compose.runtime.livedata.observeAsState
import com.examplecafe.littlelemon.auxiliary.DishData

//needed to save information about dish, and move it to another screen
var transportTempDish: DishData = DishData(-1, "empty", "empty", -1.0, "empty", "empty", 0)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {

                val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())

                var menuItems = databaseMenuItems
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyNavigation(menuItems)
                }
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuItemDao().isEmpty()) {
                saveMenuToDatabase(fetchMenu())
                Log.d("test2", fetchMenu().toString())
            }
        }
    }

    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private suspend fun fetchMenu(): List<MenuItemNetwork> {//MenuNetwork
        var resp =
            httpClient
                .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
                .body<MenuNetwork>()
        return resp.menu ?: listOf() // resp
    }

    private val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()
    }

    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }
}

@Composable
fun MyNavigation(items: List<MenuItemRoom>) {
    val context = LocalContext.current
    val sharedPreferences =
        context.getSharedPreferences(
            "userData",
            Context.MODE_PRIVATE
        )
    val orderDisplay =
        sharedPreferences.getBoolean("dataFilled", false)

    val startDes: String = if (orderDisplay) HomeDes.route else RegistrationDes.route

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDes) {
        composable(RegistrationDes.route) {
            RegistrationScreen(navController)
        }
        composable(HomeDes.route) {
            Home(navController = navController, items)
        }
        composable(SingleMenuDishDes.route) {
            SingleMenuDish(navController = navController, transportTempDish)
        }
        composable(ProfileDes.route){
            Profile(navController = navController)
        }
        composable(CartDes.route){
            Cart(navController = navController)
        }
    }
}

