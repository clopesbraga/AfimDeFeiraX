package com.example.afimdefeirax

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.afimdefeirax.ViewModel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var viewmodel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewmodel = ViewModelProvider(this).get(LoginViewModel::class.java)


    }

    fun onClick(v: View) {

        val id = v.id

        if (id == R.id.btn_salvar) {

            val usuario = findViewById<EditText>(R.id.edt_email)
            val nome = findViewById<EditText>(R.id.edt_nome)
            val senha = findViewById<EditText>(R.id.edt_senha)

            viewmodel.login(
                id,
                usuario.text.toString(),
                nome.text.toString(),
                senha.text.toString()
            )

        }

    }

}