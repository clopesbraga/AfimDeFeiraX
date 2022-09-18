package com.example.afimdefeirax.SharedPreferences

interface ISharedPreferences {


    fun storeString(key: String, str: String){

    }

    fun getString(key: String):String{

        return ""
    }

}