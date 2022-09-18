package com.example.afimdefeirax.ViewModel

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.afimdefeirax.Model.ComprasModel
import com.example.afimdefeirax.Model.LoginModel
import com.example.afimdefeirax.Repository.ComprasRepository
import com.example.afimdefeirax.Repository.LoginRepository
import com.example.afimdefeirax.SharedPreferences.LoginShared

class ComprasViewModel (application: Application):AndroidViewModel(application) {

    private val mComprasRepository = ComprasRepository(application.applicationContext)

    var mSaveCompra = MutableLiveData<Boolean>()

    fun registroCompra(id:Int,produto: String, usuario: String, nome: String) {
        val modelocompra = ComprasModel().apply {
            this.id = id
            this.produto = produto
            this.qtd = qtd


        }
        mSaveCompra.value = mComprasRepository.save(modelocompra)
    }

}