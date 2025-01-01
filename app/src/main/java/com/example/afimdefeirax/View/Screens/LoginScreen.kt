package com.example.afimdefeirax.View.Screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.afimdefeirax.Utils.Monitoring
import com.example.afimdefeirax.R
import com.example.afimdefeirax.ViewModel.LoginViewModel
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import org.koin.androidx.compose.koinViewModel
import androidx.compose.ui.text.input.PasswordVisualTransformation as PasswordVisualTransformation1


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        navController = rememberNavController(),
        showBottomBar = {}
    )
}

@Composable
fun LoginScreen(navController: NavHostController, showBottomBar: (Boolean)->Unit ){

    val firebase: FirebaseAnalytics = Firebase.analytics

    showBottomBar(false)
    val viewModel = koinViewModel<LoginViewModel>()
    val state by viewModel.state.collectAsState()

    firebase.logEvent(Monitoring.Login.LOGIN_SCREEN,null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF009688)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {


        Box{

            Image(
                painter = painterResource(id = R.mipmap.ic_logo_app_foreground),
                contentDescription = "Logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(16.dp)
                    .size(400.dp)
                    .clip(CircleShape)

            )
        }
        Box {
            OutlinedTextField(
                value = state.username,
                onValueChange = {  viewModel.onUsernameChange(it) },
                label = { Text(text = stringResource(R.string.input_user), color = Color.White) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    disabledBorderColor = Color.White,
                    cursorColor = Color.White,
                    focusedTextColor = Color.White,
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box {
            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.onPasswordChange(it)},
                label = { Text(stringResource(R.string.input_password), color = Color.White) },
                visualTransformation = PasswordVisualTransformation1(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors=OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    disabledBorderColor = Color.White,
                    cursorColor = Color.White,
                    focusedTextColor = Color.White,
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Box{
            OutlinedButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White,
                    disabledContentColor = Color.White,
                ),
                border = ButtonDefaults.outlinedButtonBorder(),
                enabled= !state.isLoading, onClick = {
                if(viewModel.login())
                    navController.navigate("map") },
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator()
                    navController.navigate("map")
                } else {
                    firebase.logEvent(Monitoring.Login.LOGIN_BUTTON_CLICKED,null)
                    Text(stringResource(R.string.confirm_login))
                }
            }

        }
    }

}

