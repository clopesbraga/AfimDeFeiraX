package com.example.afimdefeirax.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.afimdefeirax.Model.Historico
import com.example.afimdefeirax.Model.HistoricoModel
import com.example.afimdefeirax.Repository.HistoricoRepository
import com.example.afimdefeirax.SharedPreferences.HistoricoShared
import org.koin.java.KoinJavaComponent.inject

class HistoricoViewModel(application: Application) : AndroidViewModel(application) {

    private val msavehistorico: HistoricoRepository by inject(HistoricoRepository::class.java)
    private val historicoShared: HistoricoShared by inject(HistoricoShared::class.java)


    fun loadHistorico(): List<HistoricoModel> {
        val item = historicoShared.loadItems(getApplication())

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

    fun saveHistorico() {
        val item = historicoShared.loadItems(getApplication())
        save(item)
    }

    private fun save(item: List<Historico>) {

        val modelohistorico = HistoricoModel().apply {


            item.forEach { item ->
                this.nome = item.nome
                this.imagem = item.imagem
                this.preco3 = preco2
                this.preco2 = preco1
                this.preco1 = item.preco
            }
        }
        msavehistorico.save(modelohistorico)
        historicoShared.clearItems(getApplication())

    }

    private fun update(historicoItems: List<Historico>) {
        val historicoModels = historicoItems.map { item ->
            HistoricoModel().apply {
                val copy =msavehistorico.getAll().find { it.imagem == item.imagem }?.preco1
                val copy2 =msavehistorico.getAll().find { it.imagem == item.imagem }?.preco2
                nome = item.nome
                imagem = item.imagem
                preco3 = copy2
                preco2 = copy
                preco1 = item.preco
            }
        }
        msavehistorico.update(historicoModels)
        historicoShared.clearItems(getApplication())


    }

}