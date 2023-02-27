package com.examplecafe.littlelemon.auxiliary

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.examplecafe.littlelemon.ui.theme.LittleLemonColor
import com.examplecafe.littlelemon.ui.theme.Typography

/*The general layout of the confirm button. It takes as parameters a function for onClick,
and a text for the label on the button.*/
@Composable
fun ConfirmButton(action:()->Unit, text:String){
    Button(
        onClick = {
            action()
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = LittleLemonColor.yellow),
        modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp),
        shape = RoundedCornerShape(20)
    ) {
        Text(text = text, style = Typography.button)
    }
}