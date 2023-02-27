package com.examplecafe.littlelemon

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.examplecafe.littlelemon.auxiliary.BackHomeButtonTopBar
import com.examplecafe.littlelemon.auxiliary.DishData
import com.examplecafe.littlelemon.auxiliary.HorizontalDivider
import com.examplecafe.littlelemon.auxiliary.orderSingleton
import com.examplecafe.littlelemon.registration.WelcomeText

/*class for calculating the price of the total and (or) current dish,
    taking into account the quantity*/
class SolvePrice() {

    private var curDishesPrice = 0.0
    private var totalPrice = 0.0

    //add dish from singletom (storage for order data) calculation of the current price of a dish multiplied by its quantity
    fun addTotalPriceSingleDish(item: DishData) {
        curDishesPrice = item.price * item.count
        totalPriceAllDishes()
    }

    //save total price (calculation by steps, by dishes (as you go through the singleton))
    private fun totalPriceAllDishes() {
        totalPrice += curDishesPrice
    }

    fun getCurrentDishPriceString(): String {
        return String.format("%.2f$", curDishesPrice)
    }

    fun getCurrentDishPriceDouble(): Double {
        return curDishesPrice
    }

    fun getTotalPriceString(): String {
        return String.format("%.2f$", totalPrice)
    }

    fun getTotalPriceDouble(): Double {
        return totalPrice
    }
}

/*Screen for displaying the shopping cart*/
@Composable
fun Cart(navController: NavController) {

    val solvePrice = SolvePrice()

    Scaffold(topBar = { BackHomeButtonTopBar(navController = navController) }) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            WelcomeText(textWelcome = R.string.Cart_header)
            HeaderCartTable()
            HorizontalDivider()
            for (entry in orderSingleton.getCartMap()) {
                ContentCart(entry.value, solvePrice)
            }
            HorizontalDivider()
            TotalPriceField(solvePrice = solvePrice)
        }
    }
}

@Composable
fun HeaderCartTable() {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth(0.5F)) {
            Text(
                text = "Name of dish",
                Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
        Column(modifier = Modifier.fillMaxWidth(0.2F)) {
            Text(text = "Amt.", Modifier.fillMaxWidth(), fontWeight = FontWeight.Bold)
        }
        Column(modifier = Modifier.fillMaxWidth(0.5F)) {
            Text(text = "Price", Modifier.fillMaxWidth(), fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ContentCart(item: DishData, solvePrice: SolvePrice) {
    solvePrice.addTotalPriceSingleDish(item)
    Card() {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = 2.dp, vertical = 5.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.5F)
            ) {
                Text(text = item.title, Modifier.fillMaxWidth())
            }
            Column(modifier = Modifier.fillMaxWidth(0.2F)) {
                Text(text = item.count.toString(), Modifier.fillMaxWidth())
            }
            Column(modifier = Modifier.fillMaxWidth(0.5F)) {
                Text(text = solvePrice.getCurrentDishPriceString(), Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
fun TotalPriceField(solvePrice: SolvePrice) {
    Row(
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 10.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Total price: ${solvePrice.getTotalPriceString()}",
            modifier = Modifier
                .fillMaxWidth(1.0F),
            fontWeight = FontWeight.Bold
        )
    }
}