package com.example.afimdefeirax.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afimdefeirax.Model.Historico
import com.example.afimdefeirax.Model.Produtos
import com.example.afimdefeirax.SharedPreferences.HistoricoShared
import com.example.afimdefeirax.SharedPreferences.ListProdutosShared
import com.example.afimdefeirax.State.ProdutosUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProdutosViewModel(
    private val application: Application,
    private val produtosshared: ListProdutosShared,
    private val historicoshared: HistoricoShared,
) : ViewModel() {

    private val _state: MutableStateFlow<ProdutosUiState> = MutableStateFlow(ProdutosUiState())
    val state = _state

    var listprodutos = mutableListOf<Produtos>()
    var historico = mutableListOf<Historico>()



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
        var listprodutos =listOf<Produtos>()
        viewModelScope.launch{
            try{

                listprodutos=produtosshared.loadItems(application.applicationContext)

            }catch (e: Exception){

            }
        }
        return listprodutos
    }

    fun removeProduct(productItem: Produtos) {
        produtosshared.removeItem(application.applicationContext, productItem)
    }

}