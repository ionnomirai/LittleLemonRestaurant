package com.examplecafe.littlelemon.auxiliary

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.examplecafe.littlelemon.ui.theme.LittleLemonColor

/*Template for creating a field for entering information from the user.
    The template is application specific, which means we use the same look
    for all input fields in the application.*/
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OutlineInputLL(
    rem: MutableState<String>,
    labelText: String,
    placeholderText: String,
    imageVector: ImageVector = Icons.Default.Person,
    descriptionImageVector: String,
    paddingBottom: Int = 0,
    paddingTop: Int = 0,
    paddingStart: Int = 0,
    paddingEnd: Int = 0,
    keyboardType: KeyboardType = KeyboardType.Text
){
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = rem.value,
        onValueChange = { rem.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = paddingBottom.dp, top = paddingTop.dp, start = paddingStart.dp, end = paddingEnd.dp),
        label = { Text(text = labelText, color = LittleLemonColor.green) },
        placeholder = {
            Text(
                text = placeholderText,
                color = LittleLemonColor.gray
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = LittleLemonColor.green,
            cursorColor = LittleLemonColor.green
        ),
        leadingIcon = {
            Icon(
                imageVector = imageVector,
                contentDescription = descriptionImageVector
            )
        },
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = keyboardType),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() })
    )
}