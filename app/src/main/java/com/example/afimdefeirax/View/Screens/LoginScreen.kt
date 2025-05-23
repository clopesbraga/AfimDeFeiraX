package com.example.afimdefeirax.View.Screens


import androidx.compose.foundation.BorderStroke
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
import com.example.afimdefeirax.Utils.FirebaseAnalytics.FirebaseAnalyticsImpl
import com.example.afimdefeirax.ViewModel.LoginViewModel
import org.koin.compose.koinInject
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
fun LoginScreen(navController: NavHostController, showBottomBar: (Boolean) -> Unit) {

    showBottomBar(false)

    val firebaseanalytics: FirebaseAnalyticsImpl = koinInject()
    val viewModel: LoginViewModel = koinInject()
    val state by viewModel.state.collectAsState()

    firebaseanalytics.firebaselogEvent(Monitoring.Login.LOGIN_SCREEN_START)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF009688)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {


        Box {

            Image(
                painter = painterResource(id = R.mipmap.ic_app_logo_launcher_foreground),
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
                onValueChange = { viewModel.onUsernameChange(it) },
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
                onValueChange = { viewModel.onPasswordChange(it) },
                label = { Text(stringResource(R.string.input_password), color = Color.White) },
                visualTransformation = PasswordVisualTransformation1(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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
            OutlinedButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White,
                    disabledContentColor = Color.White,
                ),
                border = BorderStroke(1.dp, Color.White),
                enabled = !state.isLoading,
                onClick = {
                    firebaseanalytics.firebaselogEvent(Monitoring.Login.LOGIN_BUTTON_CLICKED)
                    if (viewModel.login()) state.isSuccess
                },
            ) {
//                if (state.isLoading) {
//                    CircularProgressIndicator(
//                        color = Color.White
//                    )
////                    navController.navigate("map")
//                } else {
//                    Text(stringResource(R.string.confirm_login))
//                }
                if(state.isLoading){ CircularProgressIndicator(color = Color.White) }
                if(state.isSuccess) navController.navigate("map")
            }

        }
    }

}

