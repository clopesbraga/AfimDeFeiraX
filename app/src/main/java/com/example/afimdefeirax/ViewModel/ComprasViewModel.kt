package com.example.afimdefeirax.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.afimdefeirax.Model.ComprasModel
import com.example.afimdefeirax.Repository.ComprasRepository

class ComprasViewModel (application: Application): ViewModel() {

    private val mComprasRepository = ComprasRepository(application.applicationContext)

    var mSaveCompra = MutableLiveData<Boolean>()

    fun registroCompra(id: Int, produto: String, qtd: String) {
        val modelocompra = ComprasModel().apply {
            this.id = id
            this.produto = produto
            this.qtd = qtd
        }
        mSaveCompra.value = mComprasRepository.save(modelocompra)
    }

    fun listaProdutos(){


    }
    private fun observe(){

    }

}