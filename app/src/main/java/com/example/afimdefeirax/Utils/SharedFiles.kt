package com.example.afimdefeirax.Utils

import android.content.Context
import android.content.SharedPreferences

class SharedFiles(context: Context) {

    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("",Context.MODE_PRIVATE)

    fun StoredFile(key : String,value: String){
        this.sharedPreferences.edit().putString(key, value).apply()
    }

    fun getStoredFile(key: String):String{
        return this.sharedPreferences.getString(key, "") ?: ""
    }

}