package com.example.afimdefeirax.ViewModel

import android.app.Application
import android.content.Context
import android.util.Log
import android.util.Log.DEBUG
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewbinding.BuildConfig.DEBUG
import com.example.afimdefeirax.Model.LoginModel
import com.example.afimdefeirax.Repository.LoginRepository

class LoginViewModel (application: Application):AndroidViewModel(application) {

    private val mLoginRepository = LoginRepository(application.applicationContext)

    var mSaveLogin = MutableLiveData<Boolean>()

    fun login(id:Int,nome: String, usuario: String, senha: String){
        val modelousuario = LoginModel().apply {
            this.id = id
            this.usuario=usuario
            this.nome = nome
            this.senha = senha
        }
        if (id==0){
            mSaveLogin.value= mLoginRepository.save(modelousuario)
        }else{
            mSaveLogin.value=mLoginRepository.update(modelousuario)
        }
    }
}