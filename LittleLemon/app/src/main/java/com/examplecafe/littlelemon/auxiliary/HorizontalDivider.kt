package com.examplecafe.littlelemon.auxiliary

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.examplecafe.littlelemon.ui.theme.LittleLemonColor

/*The single line*/
@Composable
fun HorizontalDivider(){
    Divider(
        color = LittleLemonColor.green, modifier = Modifier
            .fillMaxWidth()
            .width(10.dp)
    )
}
