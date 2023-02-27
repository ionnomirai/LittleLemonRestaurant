package com.examplecafe.littlelemon.auxiliary

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.examplecafe.littlelemon.ui.theme.LittleLemonColor
import com.examplecafe.littlelemon.ui.theme.Typography


/*The general layout of the confirm button. It takes as more parameters than simple ConfirmButton.
    But it's still the same button ConfirmButton*/
@Composable
fun ConfirmButtonFlex(
    action: () -> Unit,
    text: String,
    widthUser: Int = 170,
    paddingTop: Int = 0,
    paddingBottom: Int = 0,
    paddingStart: Int = 0,
    paddingEnd: Int = 0
) {
    Button(
        onClick = {
            action()
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = LittleLemonColor.yellow),
        modifier = Modifier
            .width(widthUser.dp)
            .padding(top = paddingTop.dp, bottom = paddingBottom.dp, start = paddingStart.dp, end = paddingEnd.dp),
        shape = RoundedCornerShape(20)
    ) {
        Text(text = text, style = Typography.button)
    }
}