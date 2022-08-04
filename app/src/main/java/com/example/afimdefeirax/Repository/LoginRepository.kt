package com.example.afimdefeirax.Repository

import android.content.Context
import com.example.afimdefeirax.Model.LoginModel

class LoginRepository(context: Context) {

    private val base = DbAfimDeFeira.getDatabase(context).loginDAO()

    fun get (id:Int):LoginModel{
        return base.get(id)
    }

    fun save (usuario:LoginModel):Boolean{
        return base.save(usuario)>0
    }

}