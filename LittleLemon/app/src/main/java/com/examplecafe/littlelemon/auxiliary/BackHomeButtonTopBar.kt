package com.examplecafe.littlelemon.auxiliary

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.examplecafe.littlelemon.HomeDes
import com.examplecafe.littlelemon.R
import com.examplecafe.littlelemon.ui.theme.LittleLemonColor

/*Button responsible for returning to the Home screen*/
@Composable
fun BackHomeButtonTopBar(navController: NavController) {
    Box(
        modifier = Modifier
            .padding(2.dp).height(50.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        )
        {
            Button(
                onClick = {
                    navController.navigate(HomeDes.route) {
                        popUpTo(HomeDes.route) {
                            inclusive = true
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = LittleLemonColor.yellow),
                shape = RoundedCornerShape(100),
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_baseline_arrow_back_24),
                    contentDescription = "Back",
                )
            }
        }
    }
}