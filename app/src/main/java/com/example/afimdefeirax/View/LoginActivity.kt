package com.example.afimdefeirax.View


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.afimdefeirax.R
import com.example.afimdefeirax.ViewModel.LoginViewModel
import com.example.afimdefeirax.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var viewmodel: LoginViewModel
    private var idUsuario = ""
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        viewmodel = ViewModelProvider(this).get(LoginViewModel::class.java)

        setContentView(binding.root)
    }

    fun onClick(v: View) {

        val id = v.id
        idUsuario = id.toString()
        if (id == R.id.btn_salvar) {

            val usuario = binding.edtEmail
            val nome = binding.edtNome
            val senha = binding.edtSenha

            viewmodel.login(
                idUsuario.toInt(),
                usuario.text.toString(),
                nome.text.toString(),
                senha.text.toString()
            )
        }
        observe()
    }

    private fun observe() {

        viewmodel.mSaveLogin.observe(this) {

            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_LONG).show()
            }
        }
        finish()
    }
}