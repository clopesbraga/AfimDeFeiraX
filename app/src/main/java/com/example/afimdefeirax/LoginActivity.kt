package com.example.afimdefeirax


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.afimdefeirax.View.MainActivity
import com.example.afimdefeirax.ViewModel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var viewmodel: LoginViewModel
    private var idUsuario :Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewmodel = ViewModelProvider(this).get(LoginViewModel::class.java)
        idUsuario=viewmodel.getId().toInt()

        verifyAcess()

    }

    fun onClick(v: View) {

        val id = v.id

        if (id == R.id.btn_salvar) {

            val usuario = findViewById<EditText>(R.id.edt_email)
            val nome = findViewById<EditText>(R.id.edt_nome)
            val senha = findViewById<EditText>(R.id.edt_senha)

            viewmodel.login(
                idUsuario,
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

    if (idUsuario<0){

        startActivity( Intent(this,MainActivity::class.java))
   }

}

}