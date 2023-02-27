package com.examplecafe.littlelemon.auxiliary

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.examplecafe.littlelemon.CartDes
import com.examplecafe.littlelemon.ProfileDes

import com.examplecafe.littlelemon.R

/*Creating a topbar containing the cart icon, logo, and user icon
    Cart and User is a clickable elements that will switch screens to
    the respective components.*/
@Composable
fun TopBar(navController: NavController){
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth().height(50.dp).padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {navController.navigate(CartDes.route){
                launchSingleTop = true
            } },

        ) {
            Image(
                painter = painterResource(id = R.drawable.cart_logo),
                contentDescription = "Cart Icon"
            )
        }

        Image(
            painter = painterResource(id = R.drawable.littlelemonimgtxt_nobg),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .fillMaxWidth(0.8F)

        )

        IconButton(onClick = {navController.navigate(ProfileDes.route){
            launchSingleTop = true
        } }) {
            Image(
                painter = painterResource(id = R.drawable.user_abstract),
                contentDescription = "user_pic"
            )
        }
    }
}