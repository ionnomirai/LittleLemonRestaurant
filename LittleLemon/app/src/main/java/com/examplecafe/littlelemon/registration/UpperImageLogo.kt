package com.examplecafe.littlelemon.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

// top logo image
@Composable
fun UpperImageLogo(namePic: Int) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = namePic),
                contentDescription = "UpperLogo",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(200.dp)
                    .padding(top = 25.dp, bottom = 25.dp)
            )
        }
}