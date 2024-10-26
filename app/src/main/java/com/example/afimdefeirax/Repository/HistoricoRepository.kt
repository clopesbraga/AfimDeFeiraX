package com.example.afimdefeirax.Repository

import android.content.Context
import com.example.afimdefeirax.Model.HistoricoModel
import com.example.afimdefeirax.Repository.Database.DbAfimDeFeira

class HistoricoRepository(context: Context) {

    private val base = DbAfimDeFeira.getDatabase(context).historicoDAO()

    fun save (historico: HistoricoModel):Boolean{
        return base.save(historico)>0
    }

    fun get (id:Int): HistoricoModel {
        return base.get(id)
    }
    fun update (historico: HistoricoModel):Boolean{
        return base.update(historico)>0
    }
    fun getAll(): List<HistoricoModel> {
        return base.getAll()
    }

}