package com.examplecafe.littlelemon

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.examplecafe.littlelemon.auxiliary.BackHomeButtonTopBar
import com.examplecafe.littlelemon.auxiliary.ConfirmButton
import com.examplecafe.littlelemon.auxiliary.ConfirmButtonFlex
import com.examplecafe.littlelemon.auxiliary.OutlineInputLL
import com.examplecafe.littlelemon.registration.UpperImageLogo
import com.examplecafe.littlelemon.registration.isValidEmail
import com.examplecafe.littlelemon.ui.theme.Typography

@Composable
fun Profile(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferences =
        context.getSharedPreferences("userData", Context.MODE_PRIVATE)

    val userName = rememberSaveable {
        mutableStateOf(sharedPreferences.getString("userName", "empty").toString())
    }
    val lastName = rememberSaveable {
        mutableStateOf(sharedPreferences.getString("lastName", "empty").toString())
    }
    val email = rememberSaveable {
        mutableStateOf(sharedPreferences.getString("userEmail", "empty").toString())
    }

    Scaffold(topBar = {
        BackHomeButtonTopBar(navController)
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Box() {
                Column {
                    UpperImageLogo(namePic = R.drawable.littlelemonimgtxt_nobg)
                    ProfileDescriptionScreen()
                }
            }
            EditProfileFiled(userName, lastName, email)
            ButtonConfirmChange(userName, lastName, email)
            ButtonLogOut(
                navController = navController,
                sharedPreferences = sharedPreferences,
            )
        }
    }
}

@Composable
fun ProfileDescriptionScreen() {
    Text(
        text = stringResource(id = R.string.Profile_decsScreen),
        style = Typography.h2,
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, top = 20.dp, bottom = 40.dp)
    )
}

@Composable
fun EditProfileFiled(firstName: MutableState<String>, lastName: MutableState<String>, email: MutableState<String>) {
    OutlineInputLL(
        rem = firstName,
        labelText = stringResource(id = R.string.Profile_firstName),
        placeholderText = stringResource(id = R.string.Registration_example_FirstName),
        descriptionImageVector = stringResource(id = R.string.Icon_description_person),
        paddingBottom = 30, paddingStart = 15, paddingEnd = 15,
    )

    OutlineInputLL(
        rem = lastName,
        labelText = stringResource(id = R.string.Profile_lastName),
        placeholderText = stringResource(id = R.string.Registration_example_LastName),
        descriptionImageVector = stringResource(id = R.string.Icon_description_person),
        paddingBottom = 30, paddingStart = 15, paddingEnd = 15,
    )

    OutlineInputLL(
        rem = email,
        labelText = stringResource(id = R.string.Profile_email),
        placeholderText = stringResource(id = R.string.Registration_example_Email),
        imageVector = Icons.Default.Email,
        descriptionImageVector = stringResource(id = R.string.Icon_description_email),
        paddingBottom = 7, paddingStart = 15, paddingEnd = 15,
        keyboardType = KeyboardType.Email
    )
}

@Composable
fun ButtonConfirmChange(firstName: MutableState<String>, lastName: MutableState<String>, email: MutableState<String>) {
    val context = LocalContext.current
    fun actionConfirmChange(){
        if (firstName.value.isNotEmpty() && lastName.value.isNotEmpty() && email.value.isValidEmail()) {
            val sharedPreferences =
                context.getSharedPreferences("userData", Context.MODE_PRIVATE)
            sharedPreferences.edit().putString("userName", firstName.value)
                .putString("lastName", lastName.value)
                .putString("userEmail", email.value)
                .putBoolean("dataFilled", true)
                .apply()
            Toast.makeText(context, "Data has been changed successfully", Toast.LENGTH_SHORT).show()
        } else if (!email.value.isValidEmail()) {
            Toast.makeText(context, "Incorrect email input!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "All fields must be completed.!", Toast.LENGTH_SHORT).show()
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 15.dp),
        horizontalArrangement = Arrangement.End
    ) {
        ConfirmButtonFlex(action = { actionConfirmChange() }, text = stringResource(id = R.string.Profile_saveChange))
    }
}

@Composable
fun ButtonLogOut(
    navController: NavController,
    sharedPreferences: SharedPreferences,
) {
    fun actionLogOut(){
        sharedPreferences.edit().putString("userName", "")
            .putString("lastName", "").putString("userEmail", "")
            .putBoolean("dataFilled", false)
            .apply()
        navController.navigate(RegistrationDes.route) {
            popUpTo(HomeDes.route) {
                inclusive = true
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, top = 50.dp),
    ) {
        ConfirmButton(action = { actionLogOut() }, text = stringResource(id = R.string.Profile_logOut))
    }
}
