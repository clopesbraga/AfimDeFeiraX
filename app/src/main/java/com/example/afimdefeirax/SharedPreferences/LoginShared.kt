package com.example.afimdefeirax.SharedPreferences

import android.content.Context
import android.content.SharedPreferences

class LoginShared(context:Context):ISharedPreferences {
    val sharedlogin:SharedPreferences=
        context.getSharedPreferences("Login",Context.MODE_PRIVATE)

    override fun storeString(key: String, str: String) {

        sharedlogin.edit().putString(key,str).apply()

    }

    override fun getString(key: String):String {

       return sharedlogin.getString(key,"")?: ""

    }
}