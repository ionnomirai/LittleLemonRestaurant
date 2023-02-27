package com.examplecafe.littlelemon.registration


import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.examplecafe.littlelemon.HomeDes
import com.examplecafe.littlelemon.R
import com.examplecafe.littlelemon.RegistrationDes
import com.examplecafe.littlelemon.auxiliary.ConfirmButton
import com.examplecafe.littlelemon.auxiliary.OutlineInputLL
import com.examplecafe.littlelemon.ui.theme.LittleLemonColor
import com.examplecafe.littlelemon.ui.theme.Typography

//Main screen for displaying the registration form
@Composable
fun RegistrationScreen(navController: NavHostController) {

/* In the form, all elements will be arranged in a column.
     * The column itself, occupies the entire width of the screen.
     * The column will contain:
     * - header - logo image, occupies approximately 10% of the screen
     * - welcome inscription
     * - general description of the fields below
     * - fields for entering information
     * - confirmation button*/
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .weight(1.0F)
                .fillMaxSize()
        ) {
            Column() {
                UpperImageLogo(namePic = R.drawable.littlelemonimgtxt_nobg)// header
                WelcomeText(R.string.Registration_welcomeText)//welcome inscription
            }
        }
        LabelFieldBelowDesc(R.string.Registration_textInputFieldsDesc)
        FieldsUserInfo(navController)//fields for entering information

    }
}

@Composable
fun WelcomeText(textWelcome: Int) {
    Text(
        text = stringResource(id = textWelcome),
        style = Typography.h1,
        modifier = Modifier
            .background(LittleLemonColor.green)
            .fillMaxWidth()
            .padding(horizontal = 40.dp, vertical = 30.dp)
    )
}

@Composable
fun LabelFieldBelowDesc(textDesc: Int) {
    Text(
        text = stringResource(id = textDesc),
        style = Typography.h2,
        modifier = Modifier.padding(top = 40.dp, bottom = 25.dp, start = 15.dp, end = 15.dp)
    )
}

@Composable
fun FieldsUserInfo(navController: NavHostController) {
    val userName = rememberSaveable {
        mutableStateOf("")
    }
    val lastName = rememberSaveable {
        mutableStateOf("")
    }
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val context = LocalContext.current

    fun actionForButtonRegister(){
        //Move to Home
        if (!userName.value.isEmpty() && !lastName.value.isEmpty() && email.value.isValidEmail()) {
            val sharedPreferences =
                context.getSharedPreferences("userData", Context.MODE_PRIVATE)
            sharedPreferences.edit().putString("userName", userName.value)
                .putString("lastName", lastName.value).putString("userEmail", email.value)
                .putBoolean("dataFilled", true)
                .apply()

            navController.navigate(HomeDes.route){
                popUpTo(RegistrationDes.route){
                    inclusive = true
                }
            }
        } else if(!email.value.isValidEmail()) {
            Toast.makeText(context, "Incorrect email input!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "All fields must be completed.!", Toast.LENGTH_SHORT).show()
        }
    }

    //fields for user input
    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp)
    ) {
        OutlineInputLL(
            rem = userName,
            labelText = stringResource(id = R.string.Registration_helpFirstName),
            placeholderText = stringResource(id = R.string.Registration_example_FirstName),
            descriptionImageVector = stringResource(id = R.string.Icon_description_person),
            paddingBottom = 30,
        )

        OutlineInputLL(
            rem = lastName,
            labelText = stringResource(id = R.string.Registration_helpLastName),
            placeholderText = stringResource(id = R.string.Registration_example_LastName),
            descriptionImageVector = stringResource(id = R.string.Icon_description_person),
            paddingBottom = 30,
        )

        OutlineInputLL(
            rem = email,
            labelText = stringResource(id = R.string.Registration_helpEmail),
            placeholderText = stringResource(id = R.string.Registration_example_Email),
            imageVector = Icons.Default.Email,
            descriptionImageVector = stringResource(id = R.string.Icon_description_email),
            paddingBottom = 30,
            keyboardType = KeyboardType.Email
        )

        ConfirmButton(action = { actionForButtonRegister() }, text = stringResource(id = R.string.Registration_confirm))

    }
}

//check if email correct
fun CharSequence?.isValidEmail() =
    !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

