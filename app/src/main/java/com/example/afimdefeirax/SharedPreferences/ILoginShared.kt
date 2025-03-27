package com.example.afimdefeirax.SharedPreferences

interface ILoginShared {


    fun storeString(key: String, str: String){

    }

    fun getString(key: String):String{

        return ""
    }

}