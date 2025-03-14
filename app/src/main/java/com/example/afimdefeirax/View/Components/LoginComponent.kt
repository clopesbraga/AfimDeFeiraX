package com.example.afimdefeirax.View.Components


import android.util.Log
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.google.firebase.auth.FirebaseAuth


@Composable
fun _SaveLoginButton(

    username: String,
    password: String,
    auth: FirebaseAuth,

) {
    OutlinedButton(onClick = {

        if (username.isEmpty()) {

            auth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener { logintTask ->
                    if (logintTask.isSuccessful) {
                    } else {
                        Log.d("CREATE_ERROR", "USER NOT SAVED -> ${logintTask.exception}")
                    }
                }
        } else {
            auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener { loginTask ->
                    if (loginTask.isSuccessful) {
                    } else {
                        Log.d("LOGIN_ERROR", "LOGIN ERROR -> ${loginTask.exception}")
                    }
                }
        }

    }) {
        Text("Login")
    }
}
