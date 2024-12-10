package com.example.afimdefeirax.View


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.afimdefeirax.ViewModel.LoginViewModel
import com.example.afimdefeirax.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import org.koin.java.KoinJavaComponent.inject
import androidx.compose.ui.text.input.PasswordVisualTransformation as PasswordVisualTransformation1


class LoginActivity : AppCompatActivity() {

    private val viewmodel: LoginViewModel by inject(LoginViewModel::class.java)
    private var idUsuario = ""
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)


        binding.composeViewLogin.setContent {

            LoginScreen(binding.root.context)
        }

        setContentView(binding.root)
    }

}

@Composable
fun LoginScreen(context: Context) {
    val auth = Firebase.auth
    var username by remember { mutableStateOf(auth.currentUser?.email ?: "") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF009688)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {
        Box {
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "Email", color = Color.White) },
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

        Box(modifier = Modifier.background(Color.Black)) {
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Enter password") },
                visualTransformation = PasswordVisualTransformation1(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Box { SaveLoginButton(username, password, auth,context) }
    }

}

@Composable
fun SaveLoginButton(

    username: String,
    password: String,
    auth: FirebaseAuth,
    context :Context

    ) {
    OutlinedButton(onClick = {

        if (username.isEmpty()) {

            auth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener { logintTask ->
                    if (logintTask.isSuccessful) {
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                    } else {
                        Log.d("CREATE_ERROR", "USER NOT SAVED -> ${logintTask.exception}")
                    }
                }
        } else {
            auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener { loginTask ->
                    if (loginTask.isSuccessful) {
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                    } else {
                        Log.d("LOGIN_ERROR", "LOGIN ERROR -> ${loginTask.exception}")
                    }
                }
        }

    }) {
        Text("Login")
    }
}
