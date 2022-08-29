package com.example.afimdefeirax.ViewModel

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.afimdefeirax.Model.LoginModel
import com.example.afimdefeirax.Repository.LoginRepository
import com.example.afimdefeirax.SharedPreferences.LoginShared

class LoginViewModel (application: Application):AndroidViewModel(application) {

    private val mLoginRepository = LoginRepository(application.applicationContext)
    private val msharedlogin = LoginShared(application.applicationContext)
    var mSaveLogin = MutableLiveData<Boolean>()

    fun login(id:Int,nome: String, usuario: String, senha: String){
        val modelousuario = LoginModel().apply {
            this.id = id
            this.usuario=usuario
            this.nome = nome
            this.senha = senha
        }
        mSaveLogin.value= mLoginRepository.save(modelousuario)
        msharedlogin.storeString("id",id.toString())
        msharedlogin.storeString("usuario",usuario)
        msharedlogin.storeString("nome",nome)
        msharedlogin.storeString("senha",senha)
    }

}