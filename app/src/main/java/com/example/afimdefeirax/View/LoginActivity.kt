package com.example.afimdefeirax.View


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.afimdefeirax.R
import com.example.afimdefeirax.ViewModel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var viewmodel: LoginViewModel
    private var idUsuario=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewmodel = ViewModelProvider(this).get(LoginViewModel::class.java)
        verifyAcess()

    }

    fun onClick(v: View) {

        val id = v.id
        idUsuario = id.toString()
        if (id == R.id.btn_salvar) {

            val usuario = findViewById<EditText>(R.id.edt_email)
            val nome = findViewById<EditText>(R.id.edt_nome)
            val senha = findViewById<EditText>(R.id.edt_senha)

            viewmodel.login(
                idUsuario.toInt(),
                usuario.text.toString(),
                nome.text.toString(),
                senha.text.toString()
            )
        }
        observe()
    }

    private fun observe(){

        viewmodel.mSaveLogin.observe(this, Observer {

            if(it){
               startActivity( Intent(this,MainActivity::class.java))
            }else {
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_LONG).show()
            }
        })
        finish()
    }

   private fun verifyAcess(){

        if (!idUsuario.isNullOrEmpty()){
            startActivity( Intent(this,MainActivity::class.java))
        }
   }

}