package com.branchh.afimdefeirax.ViewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.branchh.afimdefeirax.Model.ComprasModel
import com.branchh.afimdefeirax.Model.ListaModel
import com.branchh.afimdefeirax.Repository.ComprasRepository
import com.branchh.afimdefeirax.Repository.ListaRepository

class ListaViewModel (application: Application): ViewModel() {

    private val mListaRepository = ListaRepository(application.applicationContext)

    var mSaveLista = MutableLiveData<Boolean>()

    fun registroCompra(id: Int, produto: String, qtd: String) {
        val modeloLista = ListaModel().apply {
            this.id = id
            this.nome = produto
            this.qtd = qtd
        }
        mSaveLista.value = mListaRepository.save(modeloLista)
    }
}