package com.example.afimdefeirax.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afimdefeirax.Model.Historico
import com.example.afimdefeirax.Model.Produtos
import com.example.afimdefeirax.SharedPreferences.HistoricoShared
import com.example.afimdefeirax.SharedPreferences.ListProdutosShared
import com.example.afimdefeirax.State.ProdutosUiState
import com.example.afimdefeirax.Utils.FirebaseAnalytics.FirebaseAnalyticsImpl
import com.example.afimdefeirax.Utils.Monitoring
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProdutosViewModel(
    private val application: Application,
    private val produtosshared: ListProdutosShared,
    private val historicoshared: HistoricoShared,
    private val firebaseAnalytics: FirebaseAnalyticsImpl
) : ViewModel() {

    private val _state: MutableStateFlow<ProdutosUiState> = MutableStateFlow(ProdutosUiState())
    val state = _state

    var listprodutos = mutableListOf<Produtos>()
    var historico = mutableListOf<Historico>()

    fun takeProduts(productimage: Int, productname: String) {

        viewModelScope.launch{
            try {
                listprodutos.add(Produtos(productimage, productname))
                saveProducts(listprodutos)

            }catch (error: Exception){
                firebaseAnalytics.firebaselogEvent(Monitoring.Product.PRODUCT_CREATE_LIST_ERROR)
                Log.e(Monitoring.Product.PRODUCT_CREATE_LIST_ERROR,error.message.toString())

            }
        }
    }

    private fun saveProducts(productItem: List<Produtos>) {
        viewModelScope.launch{
            try {
                produtosshared.saveItems(application.applicationContext, productItem)

            }catch (error: Exception){
                firebaseAnalytics.firebaselogEvent(Monitoring.Product.PRODUCT_SAVE_ERROR)
                Log.e(Monitoring.Product.PRODUCT_SAVE_ERROR,error.message.toString())
            }
        }
    }

     fun requestOfHistorico(nome: String, preco: String, imagem: Int) {
         viewModelScope.launch{

             try {

                 historico.add(Historico(nome,preco,imagem))
                 historicoshared.saveItems(application.applicationContext,historico)

             }catch (error: Exception){
                 firebaseAnalytics.firebaselogEvent(Monitoring.Product.PRODUCT_REQUEST_ERROR)
                 Log.e(Monitoring.Product.PRODUCT_REQUEST_ERROR,error.message.toString())

             }

         }
    }

    fun loadProducts(): List<Produtos> {
        var listprodutos =listOf<Produtos>()
        viewModelScope.launch{
            try{

                listprodutos=produtosshared.loadItems(application.applicationContext)

            }catch (error: Exception){

                firebaseAnalytics.firebaselogEvent(Monitoring.Product.PRODUCT_LOADING_LIST_ERROR)
                Log.e(Monitoring.Product.PRODUCT_LOADING_LIST_ERROR,error.message.toString())

            }
        }
        return listprodutos
    }

    fun removeProduct(productItem: Produtos) {
        produtosshared.removeItem(application.applicationContext, productItem)
    }

}