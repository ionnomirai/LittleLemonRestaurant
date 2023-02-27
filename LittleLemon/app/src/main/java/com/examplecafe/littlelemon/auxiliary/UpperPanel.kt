package com.examplecafe.littlelemon.auxiliary

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.examplecafe.littlelemon.R
import com.examplecafe.littlelemon.ui.theme.LittleLemonColor
import com.examplecafe.littlelemon.ui.theme.Typography


/*The title bar with information about the establishment displayed on the screen Home*/
class UpperPanel {
    @Composable
    fun UpperField(name: String, city: String, descptionOne: String, idImg: Int) {
        val context = LocalContext.current
        Column(
            Modifier
                .fillMaxWidth()
                .background(color = LittleLemonColor.green),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        )
        {

            Text(
                text = name, fontSize = 40.sp, color = LittleLemonColor.yellow,
                modifier = Modifier.padding(start = 20.dp, top = 13.dp)
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 10.dp),
                horizontalArrangement = Arrangement.Start
            ) {

                Column() {
                    Text(
                        text = city, fontSize = 24.sp, color = LittleLemonColor.cloud,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )

                    Text(
                        text = descptionOne,
                        style = Typography.body1,
                        modifier = Modifier
                            .width(220.dp)
                            .padding(end = 10.dp),
                        color = LittleLemonColor.cloud,
                        textAlign = TextAlign.Justify
                    )
                }
                Column() {
                    Image(
                        painter = painterResource(id = idImg), contentDescription = "",
                        Modifier
                            .height(150.dp)
                            .clip(RoundedCornerShape(20.dp)), contentScale = ContentScale.FillWidth
                    )
                }

            }

        }
    }

    @Preview
    @Composable
    fun UpperFieldPreview() {
        UpperField(
            name = stringResource(id = R.string.Home_panel_title),
            city = stringResource(id = R.string.Home_panel_chicago),
            descptionOne = stringResource(id = R.string.Home_panel_descriptionOne),
            idImg = R.drawable.little_lemon1
        )
    }
}