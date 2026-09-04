package com.branchh.afimdefeirax.View.Screens


import android.util.Log
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
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.branchh.afimdefeirax.Utils.Monitoring
import com.branchh.afimdefeirax.R
import com.branchh.afimdefeirax.Utils.FirebaseAnalytics.FirebaseAnalyticsImpl
import com.branchh.afimdefeirax.State.LoginUiState
import com.branchh.afimdefeirax.ViewModel.LoginViewModel
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.text.input.PasswordVisualTransformation as PasswordVisualTransformation1


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    
    LoginContent(
        navController = NavHostController(LocalContext.current),
        showBottomBar = {},
        firebaseanalytics = null,
        state = LoginUiState(),
        onUsernameChange = {},
        onPasswordChange = {},
        onLogin = { false },
        onGoogleLogin = {}
    )
}

@Composable
fun LoginScreen(navController: NavHostController, showBottomBar: (Boolean) -> Unit){

    val firebaseanalytics: FirebaseAnalyticsImpl = koinInject()
    val viewModel: LoginViewModel = koinInject()
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val credentialManager = CredentialManager.create(context)

    LoginContent(
        navController = navController,
        showBottomBar = showBottomBar,
        firebaseanalytics = firebaseanalytics,
        state = state,
        onUsernameChange = { viewModel.onUsernameChange(it) },
        onPasswordChange = { viewModel.onPasswordChange(it) },
        onLogin = { viewModel.login() },
        onGoogleLogin = {
            coroutineScope.launch {
                try {
                    val googleIdOption = GetGoogleIdOption.Builder()
                        .setFilterByAuthorizedAccounts(false)
                        .setServerClientId(context.getString(R.string.default_web_client_id))
                        .build()

                    val request = GetCredentialRequest.Builder()
                        .addCredentialOption(googleIdOption)
                        .build()

                    val result = credentialManager.getCredential(
                        context = context,
                        request = request
                    )

                    val credential = result.credential
                    if (credential is androidx.credentials.CustomCredential && 
                        credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                        val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                        viewModel.signInWithGoogle(googleIdTokenCredential.idToken)
                    }
                } catch (e: GetCredentialException) {
                    Log.e("LoginScreen", "Google Sign In Error: ${e.message}")
                }
            }
        }
    )

}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun LoginContent(
    navController: NavHostController,
    showBottomBar: (Boolean) -> Unit,
    firebaseanalytics: FirebaseAnalyticsImpl?,
    state: LoginUiState,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Boolean,
    onGoogleLogin: (String) -> Unit
) {

    showBottomBar(false)


    firebaseanalytics?.firebaselogEvent(Monitoring.Login.LOGIN_SCREEN_START)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF009688)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {


        Box {

            Image(
                painter = painterResource(id = R.mipmap.ic_login_foreground),
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
                onValueChange = { onUsernameChange(it) },
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
                onValueChange = { onPasswordChange(it) },
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
                    firebaseanalytics?.firebaselogEvent(Monitoring.Login.LOGIN_BUTTON_CLICKED)
                    if (onLogin()) state.isSuccess
                },
            ) {
                if(state.isLoading){
                    CircularProgressIndicator(color = Color.White)
                } else Text(stringResource(R.string.confirm_login))
                if(state.isSuccess) navController.navigate("map")
            }

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
                    onGoogleLogin("")
                },
            ) {
                Text("Entrar com Google")
            }
        }
    }

}

