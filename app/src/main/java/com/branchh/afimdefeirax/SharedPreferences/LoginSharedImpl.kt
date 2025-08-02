package com.branchh.afimdefeirax.SharedPreferences

import android.content.Context
import android.content.SharedPreferences

class LoginSharedImpl(context:Context):ILoginShared {
    val sharedlogin:SharedPreferences=
        context.getSharedPreferences("Login",Context.MODE_PRIVATE)

    override fun storeString(key: String, str: String) {

        sharedlogin.edit().putString(key,str).apply()

    }

    override fun getString(key: String):String {

       return sharedlogin.getString(key,"")?: ""

    }
}