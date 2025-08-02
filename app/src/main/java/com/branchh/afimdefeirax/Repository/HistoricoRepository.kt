package com.branchh.afimdefeirax.Repository

import android.content.Context
import com.branchh.afimdefeirax.Model.HistoricoModel
import com.branchh.afimdefeirax.Repository.Database.DbAfimDeFeira

class HistoricoRepository(context: Context) {

    private val base = DbAfimDeFeira.getDatabase(context).historicoDAO()

    fun save (historico: HistoricoModel):Boolean{
        return base.save(historico)>0
    }

    fun get (id:Int): HistoricoModel {
        return base.get(id)
    }
    fun update (historico: List<HistoricoModel>):Boolean{
        return base.update(historico)>0
    }
    fun getAll(): List<HistoricoModel> {
        return base.getAll()
    }

}