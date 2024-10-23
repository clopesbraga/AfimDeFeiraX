package com.example.afimdefeirax.ViewModel

import android.app.Application
//import androidx.compose.ui.window.application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.afimdefeirax.Model.Historico
import com.example.afimdefeirax.Model.HistoricoModel
import com.example.afimdefeirax.Repository.HistoricoRepository
import com.example.afimdefeirax.SharedPreferences.HistoricoShared

class HistoricoViewModel(application: Application) : ViewModel() {

    private val msavehistorico = HistoricoRepository(application.applicationContext)
    private val historicoShared = HistoricoShared(application.applicationContext)
    private val application: Application = application

    fun save( preco: String, imagem: Int) {
        val modelohistorico = HistoricoModel().apply {
            this.imagem = imagem
            if(this.preco1.isEmpty().or(this.preco1<preco)){this.preco1 = preco }
            if(this.preco2.isEmpty().or(this.preco2<preco1)){this.preco2 = preco1 }
            if(this.preco3.isEmpty().or(this.preco3<preco2)){this.preco3 = preco2 }
        }
        msavehistorico.save(modelohistorico)

    }

    fun loadHistorico():List<Historico> {

        return historicoShared.loadItems(application.applicationContext)

    }
}