package com.example.afimdefeirax.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.afimdefeirax.Model.Historico
import com.example.afimdefeirax.Model.Produtos
import com.example.afimdefeirax.SharedPreferences.HistoricoShared
import com.example.afimdefeirax.SharedPreferences.ListProdutosShared
import org.koin.java.KoinJavaComponent.inject

class ProdutosViewModel(application: Application) : ViewModel() {

    val produtosshared: ListProdutosShared by inject(ListProdutosShared::class.java)
    val historicoshared: HistoricoShared by inject(HistoricoShared::class.java)
    var listprodutos = mutableListOf<Produtos>()
    var historico = mutableListOf<Historico>()

    private val application: Application = application

    fun takeProduts(productimage: Int, productname: String) {

        listprodutos.add(Produtos(productimage, productname))
        saveProducts(listprodutos)
    }

    private fun saveProducts(productItem: List<Produtos>) {
        produtosshared.saveItems(application.applicationContext, productItem)
    }

     fun requestOfHistorico(nome: String, preco: String, imagem: Int) {
        historico.add(Historico(nome,preco,imagem))
        historicoshared.saveItems(application.applicationContext,historico)
    }

    fun loadProducts(): List<Produtos> {
        return produtosshared.loadItems(application.applicationContext)
    }

    fun removeProduct(productItem: Produtos) {
        produtosshared.removeItem(application.applicationContext, productItem)
    }

}