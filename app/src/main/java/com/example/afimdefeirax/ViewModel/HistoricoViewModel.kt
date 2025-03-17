package com.example.afimdefeirax.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afimdefeirax.Model.Historico
import com.example.afimdefeirax.Model.HistoricoModel
import com.example.afimdefeirax.Repository.HistoricoRepository
import com.example.afimdefeirax.SharedPreferences.HistoricoShared
import com.example.afimdefeirax.Utils.FirebaseAnalytics.FirebaseAnalyticsImpl
import com.example.afimdefeirax.Utils.Monitoring
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class HistoricoViewModel(
    private val application: Application,
    private val historicoShared: HistoricoShared,
    private val msavehistorico : HistoricoRepository,
    private val firebaseAnalytics: FirebaseAnalyticsImpl
) : ViewModel(){

    fun loadHistorico(): List<HistoricoModel> {
        firebaseAnalytics.firebaselogEvent(Monitoring.Historic.STARTS_HISTORIC_LOAD)
        val item = historicoShared.loadItems(application)
        item.forEach { historicoItem ->
            val existingHistorico =
                msavehistorico.getAll().find { it.imagem == historicoItem.imagem }

            if (existingHistorico != null) {
                update(listOf(historicoItem))
            } else {
                save(listOf(historicoItem))
            }
        }
        return msavehistorico.getAll()
    }

    private fun save(item: List<Historico>) {

        viewModelScope.launch{
            try{
                val modelohistorico = HistoricoModel().apply {
                    item.forEach { item ->
                        this.nome = item.nome
                        this.imagem = item.imagem
                        this.preco3 = preco2
                        this.preco2 = preco1
                        this.preco1 = item.preco
                    }
                }
                firebaseAnalytics.firebaselogEvent(Monitoring.Historic.SAVE_HISTORIC)
                msavehistorico.save(modelohistorico)
                historicoShared.clearItems(application)

            }catch (error : Exception){
                firebaseAnalytics.firebaselogEvent(Monitoring.Historic.ERROR_SAVE_HISTORIC)
                Log.e(Monitoring.Historic.ERROR_SAVE_HISTORIC,error.message.toString())
            }
        }

    }

    private fun update(historicoItems: List<Historico>) {
        viewModelScope.launch{
            try {
                val historicoModels = historicoItems.map { item ->
                    HistoricoModel().apply {
                        val copy = msavehistorico.getAll().find { it.imagem == item.imagem }?.preco1
                        val copy2 = msavehistorico.getAll().find { it.imagem == item.imagem }?.preco2
                        nome = item.nome
                        imagem = item.imagem
                        preco3 = copy2
                        preco2 = copy
                        preco1 = item.preco
                    }
                }
                firebaseAnalytics.firebaselogEvent(Monitoring.Historic.UPDATE_HISTORIC)
                msavehistorico.update(historicoModels)
                historicoShared.clearItems(application)

            }catch(error: Exception){
                firebaseAnalytics.firebaselogEvent(Monitoring.Historic.ERROR_UPDATE_HISTORIC)
                Log.e(Monitoring.Historic.ERROR_UPDATE_HISTORIC,error.message.toString())
            }
        }

    }

}