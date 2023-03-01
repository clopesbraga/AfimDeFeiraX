package com.example.afimdefeirax

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.afimdefeirax.Model.LoginModel
import com.example.afimdefeirax.Repository.LoginRepository
import com.example.afimdefeirax.SharedPreferences.LoginShared
import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class LoginTest {

    val modelousuario = LoginModel().apply {
        this.id = 1
        this.usuario="usuario"
        this.nome = "nome"
        this.senha = "senha"

    }
    @Test
    fun Dado_verificacao_dos_dados() {
        assertNotNull(modelousuario.id)
        assertNotNull(modelousuario.usuario)
        assertNotNull(modelousuario.nome)
        assertNotNull(modelousuario.senha)
    }

    @Test
    fun Quando_realizar_gravacao(){



    }
}