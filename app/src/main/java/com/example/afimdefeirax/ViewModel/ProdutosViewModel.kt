package com.example.afimdefeirax.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afimdefeirax.Model.Historico
import com.example.afimdefeirax.Model.Produtos
import com.example.afimdefeirax.SharedPreferences.HistoricoShared
import com.example.afimdefeirax.SharedPreferences.ListProdutosShared
import com.example.afimdefeirax.SharedPreferences.ProductTutorialSharedImpl
import com.example.afimdefeirax.State.ProdutosUiState
import com.example.afimdefeirax.Utils.FirebaseAnalytics.FirebaseAnalyticsImpl
import com.example.afimdefeirax.Utils.Monitoring
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProdutosViewModel(
    private val application: Application,
    private val produtosshared: ListProdutosShared,
    private val historicoshared: HistoricoShared,
    private val firebaseAnalytics: FirebaseAnalyticsImpl,
    private val tutorialPreferences: ProductTutorialSharedImpl
) : ViewModel() {

    private val _state: MutableStateFlow<ProdutosUiState> = MutableStateFlow(ProdutosUiState())
    val state = _state

    var listprodutos = mutableListOf<Produtos>()
    var historico = mutableListOf<Historico>()

    init {
        val tutorialAlreadyCompleted = tutorialPreferences.hasProducTutorialBeenCompleted()
        _state.update { currentState ->
            currentState.copy(
                showTutorial = !tutorialAlreadyCompleted
            )
        }
    }


    fun onShowTutorial(){
        if(!tutorialPreferences.hasProducTutorialBeenCompleted()){
            _state.update { it.copy(showTutorial = true) }
        }
    }

    fun onTutorialCompleted() {
        _state.update { it.copy(showTutorial = false) }
        tutorialPreferences.setProductTutorialCompleted(true)
    }


    fun takeProduts(productimage: Int, productname: String) {

        viewModelScope.launch{
            try {
                firebaseAnalytics.firebaselogEvent(Monitoring.Product.PRODUCT_ITEM_SELECTED)
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
                firebaseAnalytics.firebaselogEvent(Monitoring.Product.PRODUCT_SAVE)
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
                 firebaseAnalytics.firebaselogEvent(Monitoring.Product.PRODUCT_REQUEST)
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
                firebaseAnalytics.firebaselogEvent(Monitoring.Product.PRODUCT_LOADING_LIST)
                listprodutos=produtosshared.loadItems(application.applicationContext)

            }catch (error: Exception){

                firebaseAnalytics.firebaselogEvent(Monitoring.Product.PRODUCT_LOADING_LIST_ERROR)
                Log.e(Monitoring.Product.PRODUCT_LOADING_LIST_ERROR,error.message.toString())

            }
        }
        return listprodutos
    }

    fun removeProduct(productItem: Produtos) {
        try {
            firebaseAnalytics.firebaselogEvent(Monitoring.Product.PRODUCT_REMOVE)
            produtosshared.removeItem(application.applicationContext, productItem)
        }catch (error: Exception){

            firebaseAnalytics.firebaselogEvent(Monitoring.Product.PRODUCT_REMOVE_ERROR)
            Log.e(Monitoring.Product.PRODUCT_REMOVE_ERROR,error.message.toString())

        }
    }

}