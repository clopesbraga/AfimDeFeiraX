package com.example.afimdefeirax.Repository

import android.content.Context
import com.example.afimdefeirax.Model.ListaModel
import com.example.afimdefeirax.Repository.Database.DbAfimDeFeira

class ListaRepository(context: Context) {

    private val base = DbAfimDeFeira.getDatabase(context).ListaDAO()

    fun get (id:Int): ListaModel {
        return base.get(id)
    }

    fun save (lista:ListaModel):Boolean{
        return base.save(lista)>0
    }

    fun update(id:ListaModel):Int{
        return base.update(id)
    }

}