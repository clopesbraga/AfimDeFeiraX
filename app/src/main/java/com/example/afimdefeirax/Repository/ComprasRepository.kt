package com.example.afimdefeirax.Repository

import android.content.Context
import com.example.afimdefeirax.Model.ComprasModel
import com.example.afimdefeirax.Repository.Database.DbAfimDeFeira

class ComprasRepository(context: Context) {

    private val base = DbAfimDeFeira.getDatabase(context).comprasDAO()

    fun get (id:Int):ComprasModel{
        return base.get(id)
    }

    fun save (compra:ComprasModel):Boolean{
        return base.save(compra)>0
    }

}