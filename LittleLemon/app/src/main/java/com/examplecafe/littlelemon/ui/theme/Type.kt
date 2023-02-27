package com.examplecafe.littlelemon.ui.theme


import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        color = LittleLemonColor.cloud,
    ),

    h2 = TextStyle(
        color = LittleLemonColor.green,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
    ),
    body1 = TextStyle(
        color = LittleLemonColor.green,
        fontSize = 18.sp
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.Bold,
        color = LittleLemonColor.gray,
        fontSize = 18.sp
    ),
    button = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = LittleLemonColor.green
    ),

    subtitle1 = TextStyle(
        fontSize = 18.sp,
        color = LittleLemonColor.yellow
    ),
)