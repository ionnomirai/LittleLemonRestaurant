package com.examplecafe.littlelemon

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.examplecafe.littlelemon.auxiliary.BackHomeButtonTopBar
import com.examplecafe.littlelemon.auxiliary.ConfirmButton
import com.examplecafe.littlelemon.auxiliary.DishData
import com.examplecafe.littlelemon.auxiliary.orderSingleton
import com.examplecafe.littlelemon.ui.theme.LittleLemonColor
import com.examplecafe.littlelemon.ui.theme.Typography

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SingleMenuDish(navController: NavController, dish: DishData) {

    val context = LocalContext.current

    fun actionAddToCart(){
        when (dish.count) {
            0 -> Toast.makeText(
                context,
                R.string.SingleMenuDish_zeroItems,
                Toast.LENGTH_SHORT
            ).show()
            in 1..19 -> {
                orderSingleton.addToOrder(dish)
                Toast.makeText(
                    context,
                    R.string.SingleMenuDish_successAdd,
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> Toast.makeText(
                context,
                R.string.SingleMenuDish_moreThan20Items,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Scaffold(topBar = {
        BackHomeButtonTopBar(navController)
    }) {
        if (dish.id == -1) {
            Text(text = "Sorry! Something went wrong!")
        } else {
            Column(
                modifier = Modifier
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                GlideImage(
                    model = dish.image,
                    contentDescription = dish.title,
                    modifier = Modifier
                        .fillMaxWidth().padding(bottom = 20.dp)
                )
                Text(text = dish.title, style = Typography.h2)
                Text(
                    text = dish.description,
                    style = Typography.body1,
                    textAlign = TextAlign.Justify
                )
                Counter(dish)

                ConfirmButton(action = { actionAddToCart() }, stringResource(id = R.string.SingleMenuDish_buttonAdd))
            }

        }
    }
}

@Composable
fun Counter(dish: DishData) {

    var counter by remember {
        mutableStateOf(0)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(modifier = Modifier.fillMaxWidth(0.5f)) {
            TextButton(
                onClick = {
                    if (counter > 0) {
                        dish.count = --counter
                    }
                }
            ) {
                TextSign("-")
            }
            Text(
                text = counter.toString(),
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(vertical = 30.dp, horizontal = 15.dp)
            )
            TextButton(
                onClick = {
                    dish.count = ++counter
                }
            ) {
                TextSign("+")
            }
        }
        Row(modifier = Modifier.fillMaxWidth(1F)) {
            Text(
                text = String.format("Price: %.2f$", (dish.count * dish.price)),
                style = Typography.h2,
            )
        }
    }
}

@Composable
fun TextSign(sign:String){
    Text(
        text = sign,
        fontSize = 50.sp,
        color = LittleLemonColor.yellow,
        fontWeight = FontWeight.Bold
    )
}