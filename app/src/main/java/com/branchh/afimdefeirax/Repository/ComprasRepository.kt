package com.branchh.afimdefeirax.Repository

import android.content.Context
import com.branchh.afimdefeirax.Model.ComprasModel
import com.branchh.afimdefeirax.Repository.Database.DbAfimDeFeira

class ComprasRepository(context: Context) {

    private val base = DbAfimDeFeira.getDatabase(context).comprasDAO()

    fun get (id:Int):ComprasModel{
        return base.get(id)
    }

    fun save (compra:ComprasModel):Boolean{
        return base.save(compra)>0
    }

}