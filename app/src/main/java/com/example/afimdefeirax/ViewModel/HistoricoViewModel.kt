package com.example.afimdefeirax.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.afimdefeirax.Model.HistoricoModel
import com.example.afimdefeirax.Model.ListaModel
import com.example.afimdefeirax.Repository.HistoricoRepository
import com.example.afimdefeirax.Repository.ListaRepository

class HistoricoViewModel(application:Application) : ViewModel() {

    private val mHistoricoRepository = HistoricoRepository(application.applicationContext)
    var mSaveLista = MutableLiveData<Boolean>()

    fun saveForHistorico(id: Int, imagem: Int, preco: String) {
        val modeloHistorico = HistoricoModel().apply {
            this.id = id
            this.imagem = imagem
            this.preco1 = preco
        }
        mSaveLista.value = mHistoricoRepository.save(modeloHistorico)
    }





}