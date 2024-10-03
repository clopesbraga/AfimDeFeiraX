package com.example.afimdefeirax.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.afimdefeirax.Model.Produtos
import com.example.afimdefeirax.SharedPreferences.ListProdutosShared
import org.koin.java.KoinJavaComponent.inject

class ProdutosViewModel(application: Application) : ViewModel() {

    val produtosshared: ListProdutosShared by inject(ListProdutosShared::class.java)
    var listprodutos = mutableListOf<Produtos>()

    private val application: Application = application

    fun takeProduts(productimage: Int, productname: String) {


        listprodutos.add(Produtos(productimage, productname))

        saveProducts(listprodutos)
    }

    private fun saveProducts(productItem: List<Produtos>) {
        produtosshared.saveItems(application.applicationContext, productItem)
    }

    fun loadProducts(): List<Produtos> {
      return  produtosshared.loadItems(application.applicationContext)
    }

}